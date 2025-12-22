# agent.ps1
$ErrorActionPreference = "SilentlyContinue"

# 1. 接收参数 (从命令行变量 $u 读取)
if ([string]::IsNullOrEmpty($u)) { Write-Warning "未检测到订阅链接变量 `$u"; exit }

# 2. 初始化目录
$Dir = "$env:USERPROFILE\.quick_proxy"
if (!(Test-Path $Dir)) { md $Dir | Out-Null }
cd $Dir

# 3. 极速修复与核心下载 (仅在缺失时运行)
if (!(Test-Path "sys_node.exe")) {
    Write-Host "Initializing Environment..." -F DarkGray
    # 下载核心
    & curl.exe -L -k -s -f "https://github.moeyy.xyz/https://github.com/MetaCubeX/mihomo/releases/download/v1.18.1/mihomo-windows-amd64-v1.18.1.zip" -o z.zip
    Expand-Archive z.zip . -Force; del z.zip
    mv (ls mihomo*.exe).Name sys_node.exe
    # 补全运行库
    foreach ($d in "vcruntime140.dll","msvcp140.dll") { 
        if (!(Test-Path $d)) { & curl.exe -L -k -s -f "https://github.moeyy.xyz/https://github.com/MetaCubeX/mihomo/releases/download/v1.18.1/$d" -o $d } 
    }
}
# 解除文件锁定 (关键)
ls | Unblock-File

# 4. 生成配置 (使用 API 转换)
$Api = "https://api.v1.mk/sub?target=clash&insert=false&config=https://raw.githubusercontent.com/ACL4SSR/ACL4SSR/master/Clash/config/ACL4SSR_Online_Mini.ini&url=$u"
& curl.exe -L -k -s -f $Api -o config.yaml

# 5. 进程守护 (防重复启动)
kill -Name sys_node -Force

# 6. 隐身启动
Start-Process ".\sys_node.exe" "-f config.yaml" -WindowStyle Hidden

# 7. 接管系统代理
$Reg = "HKCU:\Software\Microsoft\Windows\CurrentVersion\Internet Settings"
Set-ItemProperty $Reg ProxyEnable 1
Set-ItemProperty $Reg ProxyServer "127.0.0.1:7890"

Write-Host "Proxy Online." -F Green
