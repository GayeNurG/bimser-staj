# Log Envanteri

| Makine | Log Kaynağı | Konum | Kaydettiği Olaylar | Okuma Aracı |
|---|---|---|---|---|
| Ubuntu | Authentication Log | `/var/log/auth.log` | SSH girişleri, başarılı/başarısız kimlik doğrulama ve `sudo` işlemleri | `grep`, `tail`, `head`, `less`, `journalctl` |
| Windows | Security | Event Viewer → Windows Logs → Security | Başarılı/başarısız oturum açma, hesap işlemleri, güvenlik politikası ve yetkilendirme olayları | Event Viewer, PowerShell |
| Windows | System | Event Viewer → Windows Logs → System | İşletim sistemi, servis, sürücü ve sistem bileşenleriyle ilgili olaylar | Event Viewer, PowerShell |
| Windows | Application | Event Viewer → Windows Logs → Application | Uygulamaların oluşturduğu hatalar, uyarılar ve çalışma olayları | Event Viewer, PowerShell |
| Windows | Setup | Event Viewer → Windows Logs → Setup | Windows kurulumu ve güncelleştirme işlemleriyle ilgili olaylar | Event Viewer, PowerShell |
| Windows | Forwarded Events | Event Viewer → Windows Logs → Forwarded Events | Başka bilgisayarlardan merkezi olarak iletilen olay kayıtları | Event Viewer, PowerShell |
| Windows | Windows PowerShell | Event Viewer → Applications and Services Logs → Windows PowerShell | PowerShell oturumları, komut çalıştırma ve PowerShell ile ilgili olaylar | Event Viewer, PowerShell |
| Windows | Windows Defender | Event Viewer → Applications and Services Logs → Microsoft → Windows → Windows Defender | Güvenlik, tehdit algılama ve Microsoft Defender olayları | Event Viewer, PowerShell |
