# Pusula

## Bu Stajdan Ne Götürüyorum

**En çok ne öğrendim?**

En çok öğrendiğim şey, bir SOC'un gerçekte ne yaptığıydı. Sadece log okumak değil; farklı kaynaklardan (mail, DNS, firewall, Sysmon, auth) gelen dağınık parçaları birleştirip bir olayın hikâyesini çıkarmak, sonra bunu doğru araçlarla (Splunk, YARA, Sigma) test edilebilir hale getirip raporlamak.

**Hangi hatam bana en çok şey öğretti?**

Case 3'te ilk vaka kaydında moss.it, fosrich.com ve gönderen IP'yi hiç sorgulamadan "sorgulanmadı" diye geçmiştim. Rapor aşamasında bunu fark edip tamamladığımda, tam da bu eksik yüzünden ilginç bir bulgu çıktı: ikisi de uydurma değil, gerçek şirketlermiş (MOSS srl, FosRich Company Ltd), saldırgan onların kimliğini taklit etmiş. Gönderen IP'yi sorgulayınca da en az on farklı zararlı dosyada daha önce kullanıldığını gördüm. Bu bana şunu öğretti: bir göstergeyi "kapsam dışı" diye geçmek kolay, ama asıl hikâye çoğu zaman o sorgulanmayan yerde saklı; bir göstergeyi es geçmeden önce gerçekten kontrol etmek gerekiyor.

**Hangi refleks artık bende var, bunu nereden biliyorum?**

Phishing e-postalarını incelemiştim. Artık bir mail atıldığında, o mailin atılacağını bilsem bile içerisindeki dosyaya veya linke tıklamadan önce gönderen ismine ve mailin amacına çok dikkat ediyorum. Bunu iş dışında, kendi gündelik hayatımda da fark ediyorum.

## Altı Ay Sonra

**Hangi yönde ilerlemek istiyorum?**

Yön 1 (SIEM ve Olay Müdahalesi) ile Yön 2 (Oltalama Analizi ve Tehdit İstihbaratı) arasında kalıyorum, ikisi de ilgimi çekti. Ortak noktaları beni asıl çeken şey: vaka incelemek. Büyük ölçekli, çok kaynaklı bir olayı çözmek de, tek bir e-postanın gerçekte ne olduğunu ortaya çıkarmak da bana aynı tatmini veriyor.

**O gün kendime hangi soruları soracağım?**

- Çalışmalarım sonucunda hangi yöne doğru daha fazla ağırlık verdim?  
- Çalışmalarım beni hangi noktaya taşıdı; staj sonrasıyla bu altı aylık dönem arasında elle tutulur başarılarım var mı, ya da yeni bir araç öğrendim mi?  
- Hangi vakayı, hangi kaynağı (SigmaHQ, phishing\_pot, BOTS v3 gibi) düzenli olarak takip etmeye devam ediyorum?

