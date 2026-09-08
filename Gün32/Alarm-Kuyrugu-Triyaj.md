# Alarm Kuyruğu

## ALARM-01 – SSH Brute Force Attempt (web01)

- **Karar:** Gerçek Pozitif
- **Öncelik:** Kritik
- **Aksiyon:** Eskale et
- **Gerekçe:** Brute force başarılı oldu (deploy hesabı ele geçirildi), hemen ardından root yetkisiyle /etc/shadow okundu, kimlik bilgisi hasadı. Kaynak IP (203.0.113[.]66) dış ağdan, web01 normalde sadece iç ağdan erişiliyor.
- **Harcanan süre:** 3 dakika

## ALARM-02 – Service Account Off-Hours SSH Login (app-db01)

- **Karar:** Yanlış Pozitif
- **Öncelik:** Düşük
- **Aksiyon:** Kapat
- **Gerekçe:** svc-backup, bilinen yedekleme sunucusundan (192.168.30[.]10) publickey ile, beklenen saatte giriş yapmış, süre normal. Case 2'deki aynı hesabın anormalliği ile (parola + FIN-WS03'ten giriş) zıt, burada yöntem ve kaynak tutarlı.
- **Harcanan süre:** 4 dakika

## ALARM-03 – Credential Dumping: LSASS Memory Access (FIN-WS03)

- **Karar:** Gerçek Pozitif
- **Öncelik:** Kritik
- **Aksiyon:** Case 2 vakasına bağla
- **Gerekçe:** m.exe'nin CommandLine'ı Mimikatz'a ait (Case 2 raporunda doğrulandı), GrantedAccess=0x1410 ile LSASS erişimi bilinen credential-dumping imzası.
- **Harcanan süre:** 3 dakika

## ALARM-04 – Outbound Connection to Microsoft Update Servers (FIN-WS03)

- **Karar:** Yanlış Pozitif
- **Öncelik:** Düşük
- **Aksiyon:** Kapat
- **Gerekçe:** Gerçek svchost.exe, doğru yolda, SYSTEM hesabıyla, bilinen bir Microsoft domainine (fe2.update.microsoft[.]com, 20.99.184[.]37) bağlanıyor. Case 2'deki sahte svchost_update.exe (Temp klasöründe, farklı isim) hesabından farklı.
- **Harcanan süre:** 3 dakika

## ALARM-05 – Office Application Spawned mshta.exe (FIN-WS03)

- **Karar:** Gerçek Pozitif
- **Öncelik:** Kritik
- **Aksiyon:** Case 2 vakasına bağla
- **Gerekçe:** WINWORD.EXE'nin doğrudan mshta.exe başlatması normal kullanıcı davranışında görülmez, hedef domain (static-cdn-analytics[.]com) zaten Case 2'de C2/persistence kaynağı olarak doğrulandı.
- **Harcanan süre:** 5 dakika

## ALARM-06 – Large Outbound Data Transfer (fw01)

- **Karar:** Gerçek Pozitif (olası, kaynağı net değil)
- **Öncelik:** Kritik
- **Aksiyon:** Case 2 vakasına bağla, network görünürlük boşluğunu kapatacak ek kaynak iste
- **Gerekçe:** 480MB'lık transfer (hedef 185.220.101[.]44), aynı gün oluşturulan db_dump.tgz arşiviyle zamansal olarak örtüşüyor, ama bağlantıyı kanıtlayan bir process kaydı yok. Kesin değil, olası durum.
- **Harcanan süre:** 4 dakika

## ALARM-07 – Multiple Failed Logons, Single Account (FIN-WS12)

- **Karar:** Yanlış Pozitif
- **Öncelik:** Düşük
- **Aksiyon:** Kapat
- **Gerekçe:** 3 başarısız deneme kısa sürede, sonra başarılı, script tabanlı bir patern yerine insan yazım hatasına benziyor (zaman aralıklarından çıkarım ile). LogonType 2 ve başarılı girişte IP 127.0.0.1 olması, kullanıcının fiziksel olarak o makinenin başında olduğunu gösteriyor.
- **Harcanan süre:** 4 dakika

## ALARM-08 – Suspicious DNS Activity, Possible Tunneling

- **Karar:** Gerçek Pozitif
- **Öncelik:** Kritik
- **Aksiyon:** Case 2 vakasına bağla
- **Gerekçe:** static-cdn-analytics[.]com (çözümlenen IP: 45.137.21[.]88) zaten Case 2'de doğrulanmış C2/tünelleme domaini, TXT/NXDOMAIN paterni tünelleme imzası. Alarmın "10:09'da durmuş" notu tehdidin bittiği anlamına gelmiyor, Case 2 zaman çizelgesine göre bu saldırganın DNS kanalını kapatıp LSASS erişimine ve kalıcılığa geçtiği an.
- **Harcanan süre:** 7 dakika

## ALARM-09 – Endpoint Process Injection Alert (FIN-WS07)

- **Karar:** Veri Yetersiz
- **Öncelik:** Orta
- **Aksiyon:** Ek kaynak iste
- **Gerekçe:** Kaynak süreç adı ve erişim maskesi alanları boş geldiği için gerçek/yanlış pozitif ayrımı yapılamaz. Hedefin explorer.exe olması tek başına şüpheli olabilir ama kaynak kimliği olmadan doğrulanamaz.
- **Harcanan süre:** 8 dakika
