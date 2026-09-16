rule VB6_Crypter_TRAPFALL_Andromeda
{
    meta:
        description = "Detects VB6-based crypter/dropper stub matching TRAPFALL/Andromeda family (Case 3 quotation.iso attachment)"
        author = "Gaye Nur Gunes"
        date = "2026-09-14"
        hash_exe = "f74d3364ef9d3e3df4173a7f12d48348af44bf150c0ffcd01f0054e86ebb7e0e"
        hash_iso = "75fdb848eac332b4ca7d88f497e7ba7ebbb9a798d825b28cf1f87b9d7149e87f"
        reference = "Case3 INC-2026-0911-03"

    strings:
        $vb6_1 = "MSVBVM60.DLL" nocase
        $vb6_2 = "VBA6.DLL" nocase
        $rare_1 = "rensningsanlggene"
        $rare_2 = "noteringerne"
        $rare_3 = "physostigma"
        $rare_4 = "Nonaccretive"

    condition:
        all of ($vb6_*) and 2 of ($rare_*)
}
