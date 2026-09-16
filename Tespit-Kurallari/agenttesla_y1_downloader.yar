rule AgentTesla_Docm_Y1_Downloader
{
    meta:
        description = "Detects macro downloader fetching Y1.ps1 from cloud-storage.art"
        author = "Gaye Nur Gunes"
        date = "2026-09-10"
        hash = "8c491ff716a5b1fca672bc23db6308d080bc3aba37f7b217364f7e1a2b9e8d7c"
        reference = "MalwareBazaar, Gun 34"

    strings:
        $url = "cloud-storage.art" nocase
        $payload = "Y1.ps1" nocase
        $http_obj = "MSXML2.XMLHTTP" nocase
        $ps_flag = "ExecutionPolicy RemoteSigned" nocase

    condition:
        $url and $payload and 1 of ($http_obj, $ps_flag)
}
