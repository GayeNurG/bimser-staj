# Hafta 6 Değerlendirme Raporu

## Case 1 ile Case 2 Arasında Kendimde Ne Değişti

Case 1'de elimde zaten numaralandırılmış yedi soru vardı, ben sadece o sorulara sorguyla cevap arıyordum. Case 2'de böyle bir liste yoktu, neyi bulmam gerektiğine kendim karar vermek zorunda kaldım.

Kaynak sayısı da çok değişti. Case 1'de tek bir log dosyası vardı, tek makine. Case 2'de altı farklı log kaynağını (mail, firewall, DNS, Sysmon, Windows Security, auth.log) birbirine bağlamam gerekti, üstelik iki farklı işletim sisteminde.

Saldırının kendisi de bambaşkaydı. Case 1 tek taktikli, düz bir çizgiydi: brute force, başarılı giriş, kalıcılık için yeni kullanıcı açma. Case 2'de yedi farklı MITRE tekniği vardı, phishing'den başlayıp kimlik bilgisi hırsızlığına, kalıcılığa, lateral movement'a kadar uzanan çok aşamalı bir zincirdi.

Kanıt derecesi kavramı da ilk kez Case 2'de gerçek anlamda işime yaradı. Case 1'de her cevap auth.log'da zaten açıkça yazıyordu, çıkarım yapmama gerek yoktu. Case 2'de bazı noktaları (kalıcılığı hangi sürecin tetiklediği, 480MB transferin nereden geldiği) hiçbir kaynakta bulamadım. İlk kez "görünürlük boşluğu nedir?" bunu gözlemledim.

Ve son olarak, raporu bitirdikten sonra bir kenara bırakıp tekrar okuma alışkanlığını Case 2'de kazandım. Case 1'de böyle bir adımım yoktu.

## En Çok Zorlandığım Yer

Splunk sorgularında alan adı uyuşmazlıkları yaşadım. Host sandığım yerde aslında src kullanılıyordu (firewall log'unda), EventCode yerine bazı yerlerde EventID gerekiyordu, CommandLine gibi uzun/özel karakterli alanlar table komutunda kırpılıyordu.

Bunu nasıl aştım? Filtreyi tek tek kaldırıp head 5 ile gerçek alan adlarını kontrol ederek, ve table yerine ham event'e (_raw) bakarak.

## En Gurur Duyduğum An

Tek tek log kayıtlarının birbirinden kopuk parçalar olmaktan çıkıp, hepsini birbirine bağlayıp tek bir olay örgüsü haline getirebildiğim andı. Yani senaryonun kafamda tamamlandığı, dağınık kanıtların artık bir hikâye anlattığı nokta.

## Bir Dahaki Vakada İlk Gün Farklı Ne Yapardım

Aslında bu vakanın en başında bir playbook oluşturdum, bu onu takip ettim. Bu sebeple büyük bir sorun yaşamadım, o yüzden köklü bir şey değiştirmezdim. Ama küçük bir ayarlama yapardım: ilk güne veri kaynaklarının alan şemasını (hangi alan adı neyi karşılıyor) hızlıca kontrol ederek başlardım. Bu vakada birkaç kez sorgu yazıp "no results found" ile karşılaşıp geriye dönmek zorunda kaldım, bunu en baştan çözseydim zaman kazanırdım.
