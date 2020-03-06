Installasi Project. Untuk Siswa SMK
1. Import Project ini
2. Import database "pengaduan_masyarakat.sql".
   - Buka XAMPP, aktifkan "Apache" dan "MySQL".
   - Buka "localhost/phpMyAdmin" di browser.
   - Buat database baru dengan nama "pengaduan_masyarakat".
   - Pilih menu import. Pilih "Choose File" dan cari file "pengaduan_masyarakat.sql" di folder sini.
   - Pilih "Go".
Saya asumsikan phpMyAdmin Anda menggunakan bahasa Inggris.
3. Sesuaikan dengan kebutuhan, dengan cara mengedit tampilannya.
4. Jalankan project dan jawab pertanyaan yang diberikan penilai.

Default login aplikasi:
Username: admin
Password: password

Bahasa Pemrograman   : Java
Database             : MySQL
IDE Tools            : NetBeans
Design Arsitektur    : MVC (Model View Controller)

Cara export database:
1. Pilih database Anda ("pengaduan_masyarakat"), kemudian pilih menu "Export", kemudian pilih "Go"
2. File akan terdownload ke komputer, cari file dan jadikan 1 folder dengan project.
Apabila file sudah ada, replace file lama dengan hasil export Anda

Use case:
- Pelapor mendaftar
- Pelapor membuat pengaduan
  - Pelapor dapat melihat riwayat pengaduannya

- Petugas login
- Petugas menanggapi pengaduan
  - Petugas dapat melihat riwayat tanggapan oleh diri sendiri
  
- Admin login
- Admin menanggapi pengaduan
   - Admin dapat melihat riwayat tanggapan oleh diri sendiri
   - Admin dapat mengelola (menambah, mengubah, dan menghapus)petugas
   - Admin dapat mencetak laporan
