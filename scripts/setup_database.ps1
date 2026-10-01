param(
    [string]$MySqlExe = 'C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe',
    [string]$DbUser = $env:DB_USERNAME,
    [string]$DbPassword = $env:DB_PASSWORD
)
$ErrorActionPreference = 'Stop'
if (-not $DbUser) { throw 'Hãy đặt DB_USERNAME hoặc truyền -DbUser.' }
if (-not $DbPassword) { throw 'Hãy đặt DB_PASSWORD hoặc truyền -DbPassword.' }
$env:MYSQL_PWD = $DbPassword
try {
    Get-Content -Raw -LiteralPath "$PSScriptRoot\..\database\schema_24162095.sql" | & $MySqlExe --protocol=TCP --host=127.0.0.1 --port=3306 "--user=$DbUser" --default-character-set=utf8mb4
    if ($LASTEXITCODE -ne 0) { throw 'Schema import thất bại.' }
    Get-Content -Raw -LiteralPath "$PSScriptRoot\..\database\sample-data_24162095.sql" | & $MySqlExe --protocol=TCP --host=127.0.0.1 --port=3306 "--user=$DbUser" --default-character-set=utf8mb4
    if ($LASTEXITCODE -ne 0) { throw 'Sample data import thất bại.' }
    Write-Host 'Database web24162095 đã sẵn sàng.'
} finally { Remove-Item Env:MYSQL_PWD -ErrorAction SilentlyContinue }
