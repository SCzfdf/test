$ErrorActionPreference = "Stop"

# 启用 TLS 1.2
[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12

if (-not $env:u) { exit }
$u = $env:u

$Dir = "$env:USERPROFILE\.quick_proxy"
if (!(Test-Path $Dir)) { New-Item -ItemType Directory -Path $Dir | Out-Null }
Set-Location $Dir

$exePath = "$Dir\sys_node.exe"
if (!(Test-Path $exePath)) {
    $zipUrl = "https://github.com/MetaCubeX/mihomo/releases/download/v1.18.1/mihomo-windows-amd64-v1.18.1.zip"
    $zipFile = "$Dir\sys_node.zip"
    try {
        Invoke-WebRequest -Uri $zipUrl -OutFile $zipFile -UseBasicParsing
        Expand-Archive $zipFile -DestinationPath $Dir -Force
        Remove-Item $zipFile
        $exe = Get-ChildItem $Dir -Filter "mihomo*.exe" | Select-Object -First 1
        if ($exe) { Rename-Item $exe.FullName "sys_node.exe" }
    } catch { exit }
    
    $dlls = @("vcruntime140.dll","msvcp140.dll")
    foreach ($dll in $dlls) {
        $dllPath = "$Dir\$dll"
        if (!(Test-Path $dllPath)) {
            $dllUrl = "https://github.com/MetaCubeX/mihomo/releases/download/v1.18.1/$dll"
            try { Invoke-WebRequest -Uri $dllUrl -OutFile $dllPath -UseBasicParsing } catch { }
        }
    }
}

Get-ChildItem $Dir | Unblock-File

$Api = "https://api.v1.mk/sub?target=clash&insert=false&config=https://raw.githubusercontent.com/ACL4SSR/ACL4SSR/master/Clash/config/ACL4SSR_Online_Mini.ini&url=$u"
try { Invoke-WebRequest -Uri $Api -OutFile "$Dir\config.yaml" -UseBasicParsing } catch { exit }

$proc = Get-Process sys_node -ErrorAction SilentlyContinue
if ($proc) { $proc | Stop-Process -Force; Start-Sleep -Seconds 1 }

try {
    Start-Process -FilePath $exePath -ArgumentList "-f config.yaml" -WindowStyle Hidden
    Start-Sleep -Seconds 2
    if (Get-Process sys_node -ErrorAction SilentlyContinue) {
        Set-ItemProperty "HKCU:\Software\Microsoft\Windows\CurrentVersion\Internet Settings" ProxyEnable 1
        Set-ItemProperty "HKCU:\Software\Microsoft\Windows\CurrentVersion\Internet Settings" ProxyServer "127.0.0.1:7890"
    }
} catch { }
