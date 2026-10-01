param([string]$TomcatHome = 'D:\JavaWeb\apache-tomcat-10.1.44')
$ErrorActionPreference = 'Stop'
if (-not $env:DB_USERNAME -or -not $env:DB_PASSWORD) { throw 'Hãy đặt DB_USERNAME và DB_PASSWORD trước khi chạy.' }
$projectRoot = (Resolve-Path "$PSScriptRoot\..").Path
$runtimeBase = Join-Path $projectRoot '.tomcat'
& mvn clean package
if ($LASTEXITCODE -ne 0) { throw 'Maven build thất bại.' }
New-Item -ItemType Directory -Force -Path $runtimeBase,"$runtimeBase\webapps","$runtimeBase\logs","$runtimeBase\temp","$runtimeBase\work" | Out-Null
Copy-Item -Recurse -Force -LiteralPath "$TomcatHome\conf" -Destination $runtimeBase
Copy-Item -Force -LiteralPath "$projectRoot\target\24162095_made.war" -Destination "$runtimeBase\webapps\24162095_made.war"
$env:DB_URL = 'jdbc:mysql://localhost:3306/web24162095?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Ho_Chi_Minh&allowPublicKeyRetrieval=true&useSSL=false'
$env:CATALINA_HOME = $TomcatHome
$env:CATALINA_BASE = $runtimeBase
Start-Process -FilePath "$TomcatHome\bin\startup.bat" -WindowStyle Hidden -Wait
Write-Host 'Tomcat đang khởi động. Mở http://localhost:8080/24162095_made/home'
