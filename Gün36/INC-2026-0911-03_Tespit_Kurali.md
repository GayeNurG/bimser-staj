# Case 3 Tespit Kuralı: YARA (INC-2026-0911-03)

## Seçim Gerekçesi

Bu makineden (PUR-WS02) hiç Sysmon/Security/ağ logu toplanmadı, yani Sigma'nın bakacağı bir log kaynağı yok, yazılsa bile test edilemez, kağıt üzerinde kalır. Elimizdeki tek somut artefakt bir dosya (ISO ve içindeki .exe), bu yüzden YARA daha uygun: dosyanın kendisini doğrudan tarayıp tanıyor.

## Kural

```yara
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
```

## Mantık

Sadece MSVBVM60.DLL/VBA6.DLL gibi VB6 imzalarına güvenilmiyor, çünkü bunlar meşru eski VB6 programlarında da bulunur, tek başına kullanılsa yanlış pozitif riski yüksek olur. Bunun yerine, builder'ın örnekte ürettiği anlamsız/rastgele dolgu kelimelerinden (gerçek bir programda bulunma ihtimali neredeyse sıfır olan dizeler) en az ikisi de şart koşuluyor. VB6 imzası artı en az 2 nadir dize birlikte olursa, bu kombinasyonun rastgele bir meşru dosyada çıkma ihtimali çok düşük.

## Deneme Sonuçları

**Komut:**
```
yara vb6_trapfall.yar "makave (2)_protected_68A4BB0.exe"
```

**Sonuç:** Eşleşti.
```
VB6_Crypter_TRAPFALL_Andromeda makave (2)_protected_68A4BB0.exe
```

**Komut (temiz test):**
```
echo "zararsiz test metni, MSVBVM60.DLL gecebilir ama nadir kelimeler yok" > temiz_test.txt
yara vb6_trapfall.yar temiz_test.txt
```

**Sonuç:** Hiçbir çıktı, eşleşme yok.

## Bilinen Sınırlama

Nadir dolgu dizeleri muhtemelen builder tarafından her üretimde rastgele seçiliyor. Kural bu örneği ve olası birebir kopyalarını yakalar, ailenin tüm varyantlarını garanti etmez.
