# Git Nedir?

Git, yazılım projelerinde kaynak kodların sürüm takibini yapan dağıtık bir versiyon kontrol sistemidir. Yapılan değişikliklerin kayıt altına alınmasını, eski sürümlere dönülmesini ve ekip çalışmasını kolaylaştırır.

## Git'in Avantajları

- Sürüm geçmişini takip eder
- Eski commit'lere geri dönülebilir
- Branch yapısı ile farklı geliştirmeler yapılabilir
- Ekip çalışmalarını kolaylaştırır

## GitHub Nedir?

GitHub, Git projelerini internet üzerinde barındıran bir platformdur. Kod paylaşımı, ekip çalışması, Pull Request ve Code Review süreçleri için kullanılır.

## Temel Git Komutları

| Komut | Açıklama |
|---|---|
| `git init` | Yeni bir Git deposu oluşturur |
| `git status` | Dosyaların son durumunu gösterir |
| `git add .` veya `git add dosyaAdi` | Commit'e eklenecek dosyaları hazırlar |
| `git commit -m "İlk commit"` | Yapılan değişiklikleri kayıt altına alır |
| `git branch branchAdi` | Yeni bir branch oluşturur |
| `git switch branchAdi` veya `git checkout branchAdi` | İlgili branch'e geçiş yapar |
| `git remote add origin <repo_adresi>` | Yerel projeyi GitHub reposuna bağlar |
| `git push origin master` | Yerel commit'leri GitHub'a gönderir |
| `git pull origin master` | GitHub'daki güncel değişiklikleri bilgisayara indirir |

---

Git ve GitHub, yazılım geliştirme sürecinde kodların güvenli şekilde yönetilmesini, sürüm takibini ve ekip çalışmasını kolaylaştıran temel araçlardır.
