**MANDIANT**

Mandiant, siber güvenlikte tehdit istihbaratı ve olay müdahale (incident response) alanında dünyanın en tanınmış şirketlerinden biri. 2004'te Kevin Mandia tarafından kuruldu, büyük siber saldırıları araştıran, "bir şirket hacklendiğinde çağrılan ekip" olarak biliniyor. FIN7 gibi tehdit gruplarına isim veren ve yıllarca izleyen kuruluşlardan biri. 2021'de FireEye'dan bağımsızlaştı, 2022'de Google tarafından \~5,4 milyar dolara satın alındı; şu an Google Cloud bünyesinde ama kendi markasıyla ayrı çalışıyor. 

Mandiant, SolarWinds saldırısını ilk tespit eden şirket olarak biliniyor. Ayrıca APT29, APT41, FIN7 gibi birçok tehdit grubuna isim veren ve profilleyen kuruluşlardan biri. 

Mandiant'ın raporları sektörde referans kabul ediliyor çünkü teorik değil, doğrudan gerçek olay müdahale vakalarından besleniyor. Yani sahada gözlemlenen gerçek saldırgan davranışını belgeliyorlar. Bir raporun kaynağının kim olduğunu bilmek, o raporun güvenilirliğini değerlendirmenin ilk adımı; Mandiant gibi kaynaklar, dün konuştuğumuz titiz attribution metodolojisi sayesinde güvenilirlik kazanıyor. 

**SolarWinds Saldırısı**

SolarWinds, 2020'de ortaya çıkan, siber güvenlik tarihinin en büyük tedarik zinciri (supply chain) saldırılarından biri. Devlet destekli bir Rus grubu (APT29/Cozy Bear), SolarWinds'in Orion adlı yazılımının geliştirme sürecine sızdı ve resmi, imzalı güncelleme paketlerine SUNBURST adlı bir backdoor enjekte etti. Orion dünya çapında \~18.000 kurum tarafından kullanıldığı için (Fortune 500 şirketleri, ABD Hazine Bakanlığı gibi devlet kurumları dahil), saldırı çok geniş bir kitleye ulaştı. Kurumlar güncellemeyi güvenilir, resmi bir kaynaktan indirdikleri için hiç şüphelenmediler.

**Örnek:**

Saldırıyı Mandiant (o zamanki adıyla FireEye) kendi ağında tespit etti. Kendi red team araçlarının çalındığını fark edip araştırırken, bunun çok daha büyük, dünya çapında bir kampanyanın parçası olduğunu ortaya çıkardılar.

**Neden bir analist için önemli?**

Tedarik zinciri saldırısı kavramının en bilinen örneği. Saldırgan hedefi doğrudan değil, hedefin güvendiği bir tedarikçiyi ele geçirerek vuruyor. Bu, "imzalı/resmi bir kaynaktan geldi" diye bir şeyin otomatik olarak güvenli sayılamayacağını gösteriyor.

**FIN7 Attribution Metodolojisi (Mandiant)**

Mandiant, şüpheli bir tehdit grubunu (UNC) FIN7'nin ana kümesine dahil etmeden önce tek bir kanıta güvenmiyor; dört kanıt katmanının (altyapı, saldırı tekniği/tradecraft, çalışma biçimi/modus operandi, kod kullanımı) örtüşmesini arıyor. Bunun sebebi siber suç dünyasının akışkan olması, aynı kişi/grup farklı zamanlarda farklı ekiplerle çalışabiliyor, hatta bazen birden fazla farklı grup aynı kurbanı saatler arayla hedef alabiliyor. Bu yüzden "teknik benziyor" tek başına yetmez, kanıtın titizlikle belgelenmesi gerekiyor.  
**Örnek:**

Mandiant şu an 17 farklı grubu "FIN7 ile bağlantılı olabilir" diye şüpheleniyor ama bunları henüz resmi olarak dahil etmemiş, kanıt eşiği karşılanmadığı için. Buna karşın 8 grup, 2020'den beri aktif oldukları ve dört kanıt katmanı örtüştüğü için resmen FIN7'ye dahil edilmiş. Yani şüphe ile atfetme (attribution) arasında net, isimlendirilmiş bir eşik var.

**Neden bir analist için önemli?**

Bir bulguyu kesin diye sunmadan önce, kaç bağımsız kanıt türüyle desteklendiği sorgulanmalı. Tek bir örtüşme (örn. sadece kod benzerliği) yeterli değil. Kendi raporumda da bu mantığı uyguladım: örneğin "svc-backup meşru mu kötüye mi kullanıldı" sorusunda kesin bir yargıya varmak yerine, hangi kanıtın eksik olduğunu (farklı kimlik doğrulama yöntemi \+ farklı kaynak IP, ama meşru bakım ihtimalini dışlayamama) açıkça belirtmek, Mandiant'ın burada gösterdiği disiplinle aynı.

**PowerShell Archaeology: FIN7'nin Yürütme Alışkanlıkları (Mandiant)**

FIN7'nin en belirgin imzası PowerShell kullanımı. Farklı zararlı yazılımları farklı dillerde yazsalar da, kurbanın sistemiyle etkileşimde neredeyse hep kendilerine özgü PowerShell komut satırı parametreleri kullanıyorlar (-noni \-nop \-exe bypass \-f, \-ex bypass \-f gibi). Bu parametre kombinasyonları dünya genelinde çok nadir görülüyor. Yani bir logda görülmesi tek başına güçlü bir parmak izi sayılabiliyor. Ayrıca POWERTRASH adında, açık kaynak PowerSploit framework'ünün özelleştirilmiş bir türevi olan, bellekte çalışan bir loader'ları var; bunu CARBANAK, DICELOADER, BEACON gibi araçları çalıştırmak için kullanıyorlar.

**Örnek:**

\-ex bypass \-f parametre kombinasyonu 2800'den fazla kez görülmüş ve hepsi FIN7'ye atfedilmiş. Yani bu kombinasyon dünya genelinde neredeyse sadece FIN7'de var. POWERTRASH ise sıfırdan yazılmamış, GitHub'daki halka açık bir shellcode invoker'ı obfuscate ederek özelleştirilmiş.

**Neden bir analist için önemli?**

Nadir görülen komut satırı parametre kombinasyonları, tek başına güçlü bir tespit imzası olabiliyor. Global prevalence'ı düşük bir örüntü, geniş bir "encoded komut çalıştı" tespitinden çok daha spesifik ve güvenilir. Kendi vakamdaki Bulgu 2'de "powershell.exe encoded komutla başlatıldı" diye yazmıştım, ama tam parametreleri (-enc, \-w hidden gibi) not etmemiştim. Bu okuma bana gösterdi ki, gelecekte benzer bir saldırıyı bir başkasından ayırt etmek için, sadece "encoded komut var" demek yerine tam parametre setini kaydetmek gerekiyor.

**FIN7's Evasion: Obfuscation (Kod Gizleme) (Mandiant)**

FIN7, tarihsel olarak yaratıcı kod gizleme (obfuscation) ve hızlı evasion geliştirmeleriyle tanınıyor. Özellikle LOADOUT adlı indirici aracı sürekli evrim geçirmiş: önce kod rastgele "çöp kod" ile karıştırılmış (statik imzaları şaşırtmak için); antivirüsler bunu yakalayınca string'lerin arasına "FUCKAV" kelimesi serpiştirilmiş (hem imzayı bölmek hem alay etmek için); sonra asıl komut özel bir şifreleme mekanizmasıyla saklanmış; en son versiyonda ise birden fazla gizleme katmanı eklenmiş (doldurma metni olarak İncil ayetleri kullanılmış, çünkü rastgele metin istatistiksel olarak şüphe uyandırabiliyor, gerçek metin daha doğal görünüyor).

**Örnek:**

Mandiant, VirusTotal'da "FUCKAV" izini takip ederek FIN7'nin geliştirme aşamasındaki bir varyantını buluyor (sadece 1 antivirüs motoru tespit ediyormuş). İki saat sonra, aynı komut özel şifrelemeyle gizlenmiş yeni bir versiyon yükleniyor. Mandiant bunu, FIN7'nin kendi obfuscation'ını herkese açık bir platformda kendi kendine test etmesi olarak yorumluyor.

**Neden bir analist için önemli?**

Obfuscation statik değil, sürekli evrim geçiren bir "silahlanma yarışı". Bugün tespit edilen bir imza yarın değişmiş olabilir, bu yüzden sadece statik imza tabanlı tespit yeterli değil, davranışsal tespit de gerekiyor.

**FIN7 IOC Tablosu (Mandiant)**

Mandiant'ın IOC tablosu sadece iki sütundan oluşuyor: gösterge (hash ya da domain) ve bu göstergenin hangi araç/kampanyaya ait olduğu. Ayrı bir "değerlendirme" veya "kanıt" sütunu yok, çünkü bu bir olayı kanıtlamak için değil, birden fazla saldırıdan toplanan geniş bir referans listesi olarak hazırlanmış. Amacı dünya çapındaki analistlere "bunları kendi ağınızda görürseniz muhtemelen FIN7 var" diye arama listesi vermek. İçerik olarak hash'ler (POWERPLANT, LOADOUT, BEACON gibi araç ailelerine ait), C2 domain'leri (kasıtlı sıradan/masum görünen isimler, \[.\]com şeklinde köşeli parantezle yazılmış) ve bir trojanize edilmiş installer örneği var.

**Örnek:**

modestoobgyn\[.\]com, myshortbio\[.\]com gibi domain'ler dikkat çekmemek için gerçek bir işletme veya kişisel blog gibi görünecek şekilde seçilmiş. \[.\]com yazımı, metin kopyalanıp yapıştırıldığında yanlışlıkla tıklanabilir link olmasını engelleyen standart bir güvenlik pratiği.

**Neden bir analist için önemli?**

Bu tür yayınlanmış IOC listeleri gerçek dünyada threat hunting'de doğrudan kullanılıyor. Bir domain veya hash'i kendi loglarında görürsen anında bilinen bir tehdit grubuyla eşleştirme imkânı veriyor. Amaç farkı önemli: bu tablo tek bir olayı kanıtlamak için değil, bir grubun tüm bilinen cephanesini kataloglamak için hazırlanmış, o yüzden her satırın kesinliği yayınlanmadan önce zaten doğrulanmış oluyor.

**FIN7 MITRE ATT\&CK Eşlemesi (Mandiant)**

Mandiant, 2020-2021 arasında gözlemlediği FIN7 tekniklerini MITRE ATT\&CK taktik kategorilerine göre gruplamış: sadece teknik ID ve adı listelenmiş, her teknik için ayrı bir davranış açıklaması veya confidence seviyesi yok. Toplam 9 taktik kategorisi var: Execution, Initial Access, Impact, Resource Development, Defense Evasion, Collection, Lateral Movement, Command and Control, Discovery, Credential Access. Bu genişlik, FIN7'nin tek bir saldırı zincirinden değil, birçok farklı gerçek olaydan toplanan çok yönlü bir teknik dağarcığına sahip olduğunu gösteriyor.

**Örnek:**

Execution kategorisinde beş farklı script/komut dili var (PowerShell, Windows Command Shell, Visual Basic, JavaScript). Yani FIN7 tek bir dile bağlı kalmıyor. Defense Evasion kategorisi en kalabalık kategorilerden biri (13 teknik): Regsvr32, Rundll32, Process Injection, Reflective Code Loading gibi birçok farklı kaçınma yöntemi kullanıyorlar.

**Neden bir analist için önemli?**

Bu format, bir tehdit grubunun davranış profilini (yani ne yapabilecekleri, hangi kategorilerde ne kadar çeşitliliğe sahip oldukları) hızlıca görmek için kullanışlı, tek bir olayı detaylandırmak için değil. Bir SOC analisti bu tür bir eşlemeye baktığında, "bu grup Defense Evasion'da çok güçlü, o yüzden statik tespite güvenmemeliyim" gibi savunma stratejisi çıkarımları yapabilir. Kategori bazlı gruplama, teknik bazlı açıklamadan daha az detay verir ama daha geniş bir resim çizer.

**Yan Yana Okuma**

Belirsizliğin ifadesi:

Mandiant hiçbir yerde ikili (OBSERVED/INFERRED) bir etiket sistemi kullanmıyor; belirsizliği doğrudan cümlenin içine, kalibre edilmiş kelimelerle gömüyor. Attribution için kaç bağımsız kanıt katmanına dayandıklarını da açıkça belirtiyorlar. Bir grubu kesin listeye almadan önce altyapı, saldırı tekniği, çalışma biçimi ve kod kullanımının hepsinin örtüşmesini arıyorlar. Yani "şüpheli" ile "kesin" arasında isimlendirilmiş, somut bir eşik var.

Benim raporum daha ikili ve yapısal: OBSERVED/INFERRED etiketi \+ MITRE tablosunda Yüksek/Orta/Düşük confidence sütunu. Daha az esnek ama daha tutarlı ve denetlenebilir. Mandiant'ın düz yazıya gömülü dili daha nüanslı ama benimki kadar taranabilir değil.

Yapı ve sunum:

IOC tablosu onlarda çok daha sade. Sadece gösterge ve kısa bir not. Benim tablom değerlendirme ve kanıt sütunlarıyla daha zengin, çünkü amaç farklı: onlarınki bir grubun tüm cephanesini kataloglayan referans listesi, benimki tek bir olayı kanıtlayan delil zinciri.

MITRE eşlemesi tam tersi yönde farklı: onlar teknikleri sadece taktik başlığı altında çıplak liste halinde veriyor, teknik başına davranış açıklaması yok. Benim tablom her teknik için gözlemlenen davranışı ve confidence seviyesini içeriyor: bu noktada benimki daha detaylı.

Zaman çizelgesi kurgusu onlarda yok; olay tema bazlı anlatılmış, çünkü çok kampanyalı bir rapor. Benim raporum tek bir olayı anlattığı için saat saat zaman çizelgesi daha uygun.  
Teknik tanıma:

Kendi vakamda gördüğüm ve raporda da geçen teknikler: spearphishing attachment, web protocols üzerinden C2, SSH ile lateral movement, arşivleme, masquerading (farklı alt teknik ama aynı ana kategori).

Görmediğim şeyler de öğretici: onların credential access yöntemleri (Kerberoasting, tarayıcıdan şifre çalma) benimkinden (LSASS memory dumping) farklı. DNS tünelleme bulgum raporda hiç yok. FIN7'nin C2'si ağırlıklı HTTPS üzerinden. Scheduled task da yok, onlar farklı bir persistence yöntemi kullanmış. 

Tek somut iyileştirme:

Mandiant'ın en dikkat çeken alışkanlığı, belirsizliği sadece etikette bırakmayıp düz yazının içine işlemesiydi. Sonuç bölümündeki kapanış cümlesine bunu uyguluyorum.   
