# Log Envanteri

| Makine | Log Kaynağı | Konum | Kaydettiği Olaylar | Okuma Aracı |
|---|---|---|---|---|
| Ubuntu | Authentication Log | `/var/log/auth.log` | SSH girişleri, başarılı/başarısız kimlik doğrulama ve `sudo` işlemleri | `grep`, `tail`, `head`, `less`, `journalctl` |
| Windows | Security | Event Viewer → Windows Logs → Security | Başarılı/başarısız oturum açma, hesap işlemleri ve güvenlik olayları | Event Viewer, PowerShell |
| Windows | System | Event Viewer → Windows Logs → System | Sistem, servis, sürücü ve işletim sistemi olayları | Event Viewer, PowerShell |
| Windows | Application | Event Viewer → Windows Logs → Application | Uygulama hataları ve uygulamaların oluşturduğu olaylar | Event Viewer, PowerShell |
