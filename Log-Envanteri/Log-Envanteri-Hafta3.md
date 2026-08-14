# Log Envanteri

| Makine | Log Kaynağı | Konum | Kaydettiği Olaylar | Okuma Aracı | Saklama Süresi |
|---|---|---|---|---|---|
| Ubuntu | Authentication Log | `/var/log/auth.log` | SSH girişleri, başarılı/başarısız kimlik doğrulama ve `sudo` işlemleri | `grep`, `tail`, `head`, `less`, `journalctl` | 4 hafta |
| Windows | Security | Event Viewer → Windows Logs → Security | Başarılı/başarısız oturum açma ve güvenlik olayları | Event Viewer, PowerShell | Sabit değil; log boyutu ve üzerine yazma ayarına bağlı |
| Windows | System | Event Viewer → Windows Logs → System | Sistem, servis ve sürücü olayları | Event Viewer, PowerShell | Sabit değil; log boyutu ve üzerine yazma ayarına bağlı |
| Windows | Application | Event Viewer → Windows Logs → Application | Uygulamaların oluşturduğu hata, uyarı ve çalışma olayları | Event Viewer, PowerShell | Sabit değil; log boyutu ve üzerine yazma ayarına bağlı |
| Windows | Sysmon | Event Viewer → Applications and Services Logs → Microsoft → Windows → Sysmon → Operational | Process Create (1), Network Connection (3), File Create (11) ve DNS Query (22) gibi olayları ayrıntılı kaydeder | Event Viewer, PowerShell | Sabit değil; log boyutu ve üzerine yazma ayarına bağlı |
