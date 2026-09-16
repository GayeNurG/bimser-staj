# Tespit Kuralları

Bu klasördeki kurallar, Bimser stajı sırasında (Gün 34-35 ve Case 3) çözülen gerçek ve sentetik vakalardan çıkan bulguların tespit kurallarına dönüştürülmüş hâlidir. YARA kuralları, Gün 34 ve Case 3'te incelenen gerçek zararlı örneklere (MalwareBazaar, rf-peixoto/phishing_pot) karşı Ubuntu sanal makinede `yara` komut satırı aracıyla test edildi. Sigma kuralları, Case 2'nin sentetik Splunk verisine (`index=case2`) karşı `sigma-cli` ile Splunk SPL'ine çevrilip çalıştırıldı; pipeline olarak `-p sysmon` kullanıldı. Volt Typhoon kuralı, CISA AA24-038A danışmanlığından çıkarılmış bir taslak, henüz gerçek veriye karşı test edilmedi.

## Kural Listesi

### 1) `office_spawning_mshta.yml`
- **Yakaladığı davranış:** Bir Microsoft Word sürecinin `mshta.exe` başlatması (Office → LOLBin proxy execution)
- **Log kaynağı:** Sysmon EventID 1 (process_creation)
- **Yanlış pozitif notu:** Bilinen meşru bir senaryo yok, çok nadir bir istisna dışında (özel bir doküman şablonunun mshta çağırması); ek filtre eklenmedi, gerekmedi
- **Test durumu:** Case 2 verisinde çalıştırıldı ve yakaladı (09:41:18 kaydıyla eşleşti)

### 2) `encoded_hidden_powershell.yml`
- **Yakaladığı davranış:** `-enc`, `-EncodedCommand`, `-w hidden`, `-windowstyle hidden`, `-nop` gibi kodlanmış/gizli parametrelerle çalıştırılan PowerShell
- **Log kaynağı:** Sysmon EventID 1 (process_creation)
- **Yanlış pozitif notu:** GPO/SCCM gibi merkezi yönetim araçları bu parametreleri meşru şekilde kullanabilir; `filter_known_admin_tools` ile bilinen SCCM istemci sürecini (CcmExec.exe) ve MMC üzerinden açılan yönetim konsollarını dışladım. Bu liste kapsamlı değil, gerçek bir ortamda genişletilmesi gerekir
- **Test durumu:** Case 2 verisinde çalıştırıldı ve yakaladı (09:41:19 kaydıyla eşleşti); filtre eklendikten sonra tekrar SPL'ye çevrilip sözdizimi doğrulandı, ama filtrenin gerçek bir SCCM ortamında yanlış pozitifi gerçekten azalttığı ayrıca test edilmedi

### 3) `lsass_credential_access.yml`
- **Yakaladığı davranış:** Bir sürecin `lsass.exe`'ye Mimikatz'a özgü bir erişim maskesiyle (`GrantedAccess=0x1410`) erişmesi
- **Log kaynağı:** Sysmon EventID 10 (process_access)
- **Yanlış pozitif notu:** Bazı antivirüs/EDR araçları benzer bir maskeyle erişebilir; `filter_known_av_edr` ile Windows Defender (MsMpEng.exe) ve Microsoft Defender for Endpoint sensörünü (MsSense.exe) dışladım. Kapsamlı değil, kaynak süreç yine de manuel doğrulanmalı
- **Test durumu:** Case 2 verisinde çalıştırıldı ve yakaladı (10:05:48 kaydıyla eşleşti); filtre eklendikten sonra SPL'ye çevirisi doğrulandı, gerçek bir AV/EDR ortamında ayrıca test edilmedi

### 4) `lsass_dump_comsvcs_minidump.yml`
- **Yakaladığı davranış:** `comsvcs.dll`'in `MiniDump` export fonksiyonu üzerinden LSASS bellek dökümü alınması (Volt Typhoon'un kullandığı living-off-the-land tekniği)
- **Log kaynağı:** Sysmon EventID 1 (process_creation)
- **Yanlış pozitif notu:** Yöneticilerin gerçek bir çökme (crash) dökümü toplamak için `comsvcs.dll`'i meşru kullanması nadir ama mümkün; bilinen bakım pencereleriyle karşılaştırılması önerilir. Ek bir filtre eklenmedi
- **Test durumu:** Taslak, hiç test edilmedi (bu ortamda çalıştıracak log yoktu, CISA danışmanlığından çıkarılan bir tasarım)

### 5) `agenttesla_y1_downloader.yar`
- **Yakaladığı davranış:** `Document_Open` ile otomatik tetiklenen, `cloud-storage.art` adresinden bir PowerShell scripti (Y1.ps1) indirip çalıştıran VB6 makrolu belge (AgentTesla downloader)
- **Log kaynağı:** Dosya (statik analiz, log kaynağı değil)
- **Yanlış pozitif notu:** Tek başına `MSVBVM60.DLL`/`VBA6.DLL` imzalarına güvenilmedi (meşru eski VB6 programlarında da bulunur); kural, bunlara ek olarak örnekte bulunan rastgele dolgu dizelerinden en az ikisini şart koşuyor
- **Test durumu:** Gerçek örneğe (vbaProject.bin) karşı çalıştırıldı ve eşleşti; boş/makrosuz bir metin dosyasına eşleşmedi

### 6) `vb6_crypter_trapfall_andromeda.yar`
- **Yakaladığı davranış:** Case 3'teki ISO ekinin içinden çıkan VB6 tabanlı crypter/dropper (TRAPFALL.exe, Andromeda ailesi)
- **Log kaynağı:** Dosya (statik analiz, log kaynağı değil)
- **Yanlış pozitif notu:** Aynı mantık, VB6 çalışma zamanı imzaları tek başına yeterli sayılmadı, örnekteki rastgele dolgu dizelerinden en az ikisi şart koşuluyor
- **Test durumu:** Gerçek örneğe karşı çalıştırıldı ve eşleşti; iki ayrı temiz/zararsız metin dosyasına (biri kasıtlı sahte pozitif testi) eşleşmedi

## Doğrulama Sonucu

`sigma check -x d3_fendtag *.yml` ile dört Sigma kuralının tamamı kontrol edildi: 0 hata, 0 koşul hatası, 0 doğrulama sorunu (`d3_fendtag` validator'ı bu ortamda ağ erişimi gerektirdiği için dışlandı; ilk geçişte iki kuralda MITRE taktik etiketlerinin biçimi hatalıydı, `_` yerine `-` kullanılarak düzeltildi). Dört `.yml` dosyası ayrıca `sigma convert -t splunk -p sysmon` ile tekrar SPL'ye çevrilip filtrelerin sözdizimini bozmadığı doğrulandı. İki `.yar` dosyası `yara` ile sözdizimi açısından derlendi, hata vermedi.
