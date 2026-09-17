# Google Dork Nedir?

Google dorking veya Google hacking, kuruluşların asla ifşa etmeyi amaçlamadığı dosyalar, dizinler ve giriş sayfaları gibi herkese açık olarak indekslenmiş kaynakları ortaya çıkarmak için özel arama operatörlerinden yararlanır. Web tarama veya DNS kaba kuvvet saldırısı gibi aktif tarama tekniklerinin aksine, dorking tamamen pasiftir; yani hedef sistemlerde gösterilen faaliyetlerin hiçbir izi kalmaz.

## Arama Operatörleri

| Operatör | Ne Yapar | Örnek |
|---|---|---|
| `site:` | Belirli bir site içinde arama yapılmasını sağlar | `site:example.com` ifadesi example.com sitesi içinde arama yapar |
| `filetype:` | Belirli dosya türlerini arar | `filetype:pdf` PDF dosyalarını döndürür |
| `intitle:` | Bir web sayfasının başlığında belirli terimleri aramak için kullanılır | `intitle:"index of"` ifadesi, dizin listeleme özelliği etkinleştirilmiş web sunucularını ortaya çıkarabilir |
| `inurl:` | Bir sayfanın URL'si içinde belirli kelimeleri bulmak için kullanılabilir | `inurl:login` ifadesi, URL'sinde 'login' kelimesi geçen sayfaları döndürür |
| `""` (tam eşleşme) | Yazılan ifadenin tam olarak aynı sırayla ve aynı kelimelerle aranmasını sağlar | `"cybersecurity"` ifadesi, mümkün olduğunca "cybersecurity" ifadesinin aynen geçtiği sayfaları getirir |
| `-` (hariç tutma) | Google arama sonuçlarından istenmeyen kelimeyi veya ifadeyi hariç tutmak için kullanılır | `python -snake` ifadesi, Python programlama dili ile ilgili sonuçları göstermeye çalışır ve yılan ile ilgili sonuçları mümkün olduğunca filtreler |
| `OR` | Her iki terim için de arama yapar | `linux OR unix` ifadesi, Linux veya Unix ile ilgili sonuçları gösterir |

## Ek

https://www.yeswehack.com/learn-bug-bounty/recon-hackers-guide-google-dorking

---

03.08.2026 tarihinde AI-Kullanim-Kurallari.md ve Lab-Kurallari.md dosyalarını okudum ve anladım.
