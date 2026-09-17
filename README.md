# 🛡️ Siber Güvenlik Blue Team Staj Günlüğü

Bu depo, Bimser'de yaptığım Blue Team stajım boyunca gün gün tuttuğum notları, hazırladığım vaka raporlarını ve yazdığım tespit kurallarını barındırıyor. Amacım sadece "staj defteri" tutmak değildi; her günü, bir sonraki günde geri dönüp bakabileceğim, kendi kendime referans olabilecek şekilde belgelemeye çalıştım.

Süreç, temel kavramlardan (encoding, hashing, şifreleme) başlayıp Linux/ağ temellerine ve log analizine, oradan olay müdahalesine (Case 1, Case 2), son olarak SOC operasyonlarına ve tespit mühendisliğine (Case 3) uzanan, kademeli olarak zorlaşan bir müfredat şeklinde ilerledi.

⚠️ **Not:** Vakalardaki kurum, kişi ve sistem adları kurgudur. Kullanılan gerçek e-posta ve zararlı yazılım örnekleri herkese açık araştırma depolarından (ör. MalwareBazaar, phishing_pot) alınmıştır. Bu depo hiçbir gerçek kurum verisi içermez.

## 🎯 Stajın Amacı ve Süresi

**Amaç:** Bir SOC analistinin günlük işleyişini, temel güvenlik kavramlarından başlayarak log analizi, olay müdahalesi, SOC operasyonları ve tespit mühendisliğine kadar uçtan uca, gerçekçi vakalar üzerinde uygulamalı olarak öğrenmek.

**Süre:** 40 iş günü (Gün 1 - Gün 40), her gün ayrı bir klasörde belgelenmiştir. Sekiz haftanın tek cümlelik özeti için: [Haftalar.md](Haftalar.md)

## 🛠️ Kullanılan Teknolojiler

- **Splunk**: SIEM sorguları (SPL) ile olay korelasyonu ve alarm triyajı
- **Sysmon**: süreç oluşturma, ağ bağlantısı ve süreç erişimi loglaması
- **Sigma / sigma-cli**: SIEM'den bağımsız tespit kuralları yazımı ve Splunk SPL'ine çevirisi
- **YARA**: dosya tabanlı statik zararlı yazılım tespiti
- **oletools** (oleid, olevba): makrolu Office belgelerinin statik analizi
- **VirtualBox**: izole Ubuntu/Windows lab ortamı, snapshot tabanlı güvenli analiz
- **Wireshark, Nmap, Nginx**: ağ trafiği yakalama ve tarama temelleri
- **CyberChef**: kodlanmış/gizlenmiş payload'ların çözümü
- **VirusTotal, MalwareBazaar, URLhaus, AbuseIPDB**: IOC zenginleştirme servisleri
- **Git / GitHub**: sürüm kontrolü ve gün gün belgeleme

## Vakalar

| Vaka | Konu | Rapor |
|---|---|---|
| Case 1 | SSH brute force ve hesap ele geçirme | [Gün20/Staj Gün-20 Vaka Olay Raporu-1.md](Gün20/Staj%20G%C3%BCn-20%20Vaka%20Olay%20Raporu-1.md) |
| Case 2 | Oltalama, mshta/LOLBin zinciri, LSASS erişimi, DNS tünelleme, yanal hareket | [Gün29/Case2_Olay_Raporu.pdf](Gün29/Case2_Olay_Raporu.pdf) |
| Case 3 | Sahte fatura e-postası ve zararlı ISO eki, yönlendirmesiz çözüldü | [Gün37/INC-2026-0911-03 .md](Gün37/INC-2026-0911-03%20.md) |

## Tespit Kuralları

Üç vakadan ve iki tehdit istihbaratı okumasından (CISA Emotet, CISA Volt Typhoon) çıkan altı kural (dört Sigma, iki YARA), her biri gerçek veriye karşı test edilmiş, yanlış pozitif notuyla birlikte [Tespit-Kurallari/](Tespit-Kurallari/) klasöründe duruyor.

## İncelenen Tehdit İstihbaratı Raporları

Kendi vakalarımla karşılaştırarak okuduğum, gerçek yayınlanmış dört rapor:

| Rapor | Konu |
|---|---|
| [Gün29/Mandiant-FIN7.md](Gün29/Mandiant-FIN7.md) | Mandiant'ın FIN7 tehdit grubu istihbarat raporu, Case 2 raporuyla karşılaştırıldı |
| [Gün31/The_DFIR_Report:IcedID_Macro_Ends_in_Nokoyawa_Ransomware.md](Gün31/The_DFIR_Report%3AIcedID_Macro_Ends_in_Nokoyawa_Ransomware.md) | Gerçek bir fidye yazılımı vakasının (IcedID'den Nokoyawa'ya) beş adımlı okuması |
| [Gün32/Emotet-CISA.md](Gün32/Emotet-CISA.md) | CISA'nın Emotet zararlı yazılım ailesi danışmanlığı (AA20-280A) |
| [Gün35/Volt_Typhoon_AA24-038A.md](Gün35/Volt_Typhoon_AA24-038A.md) | CISA'nın Volt Typhoon danışmanlığı (AA24-038A), devlet destekli bir grubun living-off-the-land tekniği |

## Gün Gün İçerik

Blok 1, Temeller (Gün 1-10)

| Gün | Konu |
|---|---|
| 1 | Git ve GitHub kullanımı, sürüm kontrolü temelleri |
| 2 | Encoding ve Obfuscation, Base64/URL/Hex, CVE-2021-41773/42013, IDOR |
| 3 | Log4Shell, Hashing, hash collision, salt, rainbow table, hashcat |
| 4 | Encryption: simetrik (AES) ve asimetrik (RSA/ECC) şifreleme, TLS |
| 5 | Bilgi güvenliği temelleri, CIA üçlüsü, phishing, ISO 27001 |
| 6 | Google Dorking, arama operatörleri, lab ve AI kullanım kuralları |
| 7 | OSI ve TCP/IP modelleri, kapsülleme, üçlü el sıkışma, portlar |
| 8 | IPv4 adresleme yapısı, public/private IP, CIDR, loopback |
| 9 | Nginx kurulumu, Wireshark ile trafik analizi, Nmap taraması |
| 10 | Web sitesine bağlanma süreci, ilk ağ trafiği analiz raporu |

Blok 2, Log ve Ağ (Gün 11-19)

| Gün | Konu |
|---|---|
| 11 | Linux'ta kullanıcılar, izinler (SUID/SGID), süreçler |
| 12 | Linux logları (/var/log), journalctl, log analizi |
| 13 | Windows kullanıcı/grup yapısı, SID, UAC, Event Viewer |
| 14 | Sysmon: Process Create, Network Connection, DNS Query |
| 15 | Log saklama/rotation, SSH analizi uygulaması, SIEM'e giriş |
| 16 | Saldırı türleri: brute force, password spraying, phishing, MITM |
| 17 | MITRE ATT&CK: taktik, teknik, alt teknik, lab eşlemesi |
| 18 | Ağ mimarisi: segmentasyon, VLAN, DMZ, proxy, VPN, SIEM |
| 19 | Splunk'a veri besleme ve temel SPL sorguları |

Blok 3, Olay Müdahalesi, Case 1 ve Case 2 (Gün 20-30)

| Gün | Konu |
|---|---|
| 20 | Case 1: SSH brute force ve hesap ele geçirme olay raporu |
| 21 | NIST Olay Müdahale Yaşam Döngüsü (4 aşama), Case 1 sunumu |
| 22 | Playbook kavramı, korelasyon için SPL (timechart, rex) |
| 23 | IOC, Pyramid of Pain, MITRE ATT&CK teknik eşlemesi |
| 24 | Case 2: mshta, LOLBin, lsass, DNS tünelleme analizi |
| 25 | Case 2: PID zinciri doğrulama, kalıcılık, yanal hareket |
| 26 | SPF/DKIM, masquerading, negatif kanıt, görünürlük boşluğu |
| 27 | Case 2 olay raporu: özet, kapsam, zaman çizelgesi |
| 28 | Case 2 olay raporu: bulgular, kanıtlar, öneriler |
| 29 | Mandiant, SolarWinds, FIN7 attribution metodolojisi |
| 30 | Hafta 6 değerlendirme raporu, Case 2 sunumu |

Blok 4, SOC Operasyonları (Gün 31-35)

| Gün | Konu |
|---|---|
| 31 | SOC nedir, SOC katmanları, Event/Alert/Triage, vaka defteri şablonu |
| 32 | Alarm kuyruğu triyajı (9 alarm) |
| 33 | Gerçek phishing e-postaları analizi (3 örnek) |
| 34 | Malware statik analizi (AgentTesla), YARA kuralı |
| 35 | Sigma kuralları, tespit mühendisliği, Volt Typhoon okuması |

Blok 5, Case 3 ve Portfolyo (Gün 36-40)

| Gün | Konu |
|---|---|
| 36 | Case 3: phishing e-postası ve zararlı ISO eki analizi |
| 37 | Case 3 olay raporu (yönetici özeti ve teknik ek, iki kitleli) |
| 38 | Tespit-Kurallari paketi, açık kalemlerin kapatılması |
| 39 | Portfolyo: kök README, hafta özetleri, repo ayıklaması |
| 40 | Kapanış: staj defteri ve imza |

## Repo Haritası

- `GünN/`: o günün ham notları, vaka kayıtları, varsa ekran görüntüleri ve çıktı dosyaları
- `Tespit-Kurallari/`: Sigma ve YARA kuralları ile ortak README
- `Engel-Kayitlari/`: karşılaşılan teknik engeller ve çözümleri, gün gün
- `Log-Envanteri/`: Linux ve Windows log kaynaklarının konumu, saklama süresi ve erişim notları
- `Haftalar.md`: sekiz haftanın her biri için tek cümlelik özet

## Son Not

Bu depo bir öğrenme/staj günlüğüdür, üretim ortamına yönelik bir güvenlik ürünü değildir. İçindeki tüm analizler (zararlı e-posta ve dosya incelemeleri dahil) izole bir sanal makine ortamında, snapshot ve ağ izolasyonu kurallarına uyularak yapıldı; hiçbir teknik izinsiz ya da gerçek/üretim bir sisteme karşı kullanılmadı.

---

Gaye Nur Güneş
[linkedin.com/in/gayenurgunes](https://www.linkedin.com/in/gayenurgunes)
