<h3>Aplikasi Penjualan Netbeans</h3>

<b>UAS Pemrograman Berorientasi Objek</b>

Requirement : openjdk 15.0.2, Apache Netbeans versi 14, mysql-connector-j-8.0.33.jar, dan jcalendar-1.4.jar 

Buat sebuah aplikasi Jual Beli menggunakan Bahasa pemrograman Java yang bisa melakukan pendataan data master Barang, Konsumen (Customer), dan Pemasok (Supplier). 
Melakukan pencatatan transaksi Penjualan dan Pembelian. Serta bisa menghasilkan keluaran berupa laporan untuk semua data master dan data transaksi yang ada. Pengguna aplikasi hanya Admin.

Alur program diawali dengan Admin melakukan autentifikasi melalui form login (user ID : 12345 | password : admin). Jika kombinasi user id dan password yang diinputkan tidak cocok dengan data yang ada di database maka tampilan akan tetap berada pada form login. Jika cocok maka akan muncul form yang berisi menu-menu dari aplikasi.
Menu yang ada di aplikasi adalah :
- Data Master : Barang, Konsumen, Pemasok
- Transaksi : Penjualan, Pembelian
-	Laporan : Penjualan, Pembelian

Di dalam masing-masing menu Data Master dapat melakukan proses CRUD (Create, Read, Update, Delete) data dan menghasilkan keluaran berupa laporan data dalam format file excel.

Di dalam menu Transaksi Penjualan dapat melakukan pendataan penjualan barang kepada konsumen dan mencetak nota transaksi dalam format file PDF. Relasi yang terjadi adalah antara tabel barang, konsumen, penjualan, dan detail penjualan. Kegiatan penjualan akan mengurangi jumlah stok barang.

Di dalam menu Transaksi Pembelian dapat melakukan pendataan pembelian barang kepada pemasok dan mencetak nota transaksi dalam format file PDF. Relasi yang terjadi adalah antara tabel barang, pemasok, pembelian, dan detail pembelian. Kegiatan pembelian akan menambah jumlah stok barang.

Di dalam menu Laporan dapat menampilkan data transaksi berdasarkan range tanggal dan dapat menghasilkan keluaran berupa laporan data dalam format file excel.

Jika tidak ada lagi yang akan dikerjakan, Admin menekan menu Keluar untuk menutup aplikasi.

