$ErrorActionPreference = "Stop"

# --- 0. 启用 TLS 1.2 ---
[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12

# --- 1. 读取订阅链接 ---
if (-not $env:u) {
    Write-Warning "未检测到订阅链接变量 `$u"
    exit
}

$u = $env:u

# --- 2. 初始化目录 ---
$Dir = "$env:USERPROFILE\.quick_proxy"
if (!(Test-Path $Dir)) { 
    New-Item -ItemType Directory -Path $Dir | Out-Null
}
Set-Location $Dir

# --- 3. 下载核心程序和依赖 ---
$exePath = "$Dir\sys_node.exe"
if (!(Test-Path $exePath)) {
    Write-Host "Downloading core program..." -ForegroundColor DarkGray

    # 下载 ZIP
    $zipUrl = "https://github.com/MetaCubeX/mihomo/releases/download/v1.18.1/mihomo-windows-amd64-v1.18.1.zip"
    $zipFile = "$Dir\sys_node.zip"
    try {
        Invoke-WebRequest -Uri $zipUrl -OutFile $zipFile -UseBasicParsing -ErrorAction Stop
        Expand-Archive $zipFile -DestinationPath $Dir -Force
        Remove-Item $zipFile
        Write-Host "Core program downloaded and extracted." -ForegroundColor Green
    } catch {
        Write-Warning "核心程序下载失败，请检查网络或手动下载 ZIP 文件。"
        exit
    }

    # 重命名 exe
    $exe = Get-ChildItem $Dir -Filter "mihomo*.exe" | Select-Object -First 1
    if ($exe) { Rename-Item $exe.FullName "sys_node.exe" }

    # 下载必要 DLL
    $dlls = @("vcruntime140.dll","msvcp140.dll")
    foreach ($dll in $dlls) {
        $dllPath = "$Dir\$dll"
        if (!(Test-Path $dllPath)) {
            $dllUrl = "https://github.com/MetaCubeX/mihomo/releases/download/v1.18.1/$dll"
            try {
                Write-Host "Downloading $dll..."
                Invoke-WebRequest -Uri $dllUrl -OutFile $dllPath -UseBasicParsing -ErrorAction Stop
            } catch {
                Write-Warning "$dll 下载失败，请手动下载并放置到 $Dir"
            }
        }
    }
}

# --- 4. 解除锁定 ---
Get-ChildItem $Dir | Unblock-File

# --- 5. 生成配置 ---
$Api = "https://api.v1.mk/sub?target=clash&insert=false&config=https://raw.githubusercontent.com/ACL4SSR/ACL4SSR/master/Clash/config/ACL4SSR_Online_Mini.ini&url=$u"
try {
    Invoke-WebRequest -Uri $Api -OutFile "$Dir\config.yaml" -UseBasicParsing -ErrorAction Stop
    Write-Host "配置文件生成完成。" -ForegroundColor Green
} catch {
    Write-Warning "配置文件生成失败，请检查订阅链接。"
    exit
}

# --- 6. 停止旧进程 ---
$proc = Get-Process sys_node -ErrorAction SilentlyContinue
if ($proc) { 
    $proc | Stop-Process -Force
    Start-Sleep -Seconds 1
}

# --- 7. 启动代理 ---
try {
    $startInfo = Start-Process -FilePath $exePath -ArgumentList "-f config.yaml" -WindowStyle Hidden -PassThru
    Start-Sleep -Seconds 2

    # 确认进程已启动
    if (-not (Get-Process sys_node -ErrorAction SilentlyContinue)) {
        throw "sys_node.exe 启动失败"
    }

    # --- 8. 接管系统代理 ---
    $Reg = "HKCU:\Software\Microsoft\Windows\CurrentVersion\Internet Settings"
    Set-ItemProperty $Reg ProxyEnable 1
    Set-ItemProperty $Reg ProxyServer "127.0.0.1:7890"

    Write-Host "Proxy Online." -ForegroundColor Green
} catch {
    Write-Warning $_
    Write-Host "代理启动失败，未修改系统代理。" -ForegroundColor Red
}
