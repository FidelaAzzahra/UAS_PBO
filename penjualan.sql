-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Waktu pembuatan: 17 Jul 2023 pada 09.45
-- Versi server: 10.4.28-MariaDB
-- Versi PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `penjualan`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `kd_brg` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nm_brg` char(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `satuan` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `harga` double NOT NULL,
  `stok` int(5) DEFAULT NULL,
  `stok_min` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`kd_brg`, `nm_brg`, `satuan`, `harga`, `stok`, `stok_min`) VALUES
('01', 'Boneka', 'Buah', 1000000, 592, 10),
('02', 'Asus TUF', 'Unit', 15000000, 399, 10),
('03', 'Komik Boruto', 'Pcs', 25000000, 1007, 13),
('04', 'Genesis Krystal', 'Buah', 2500000, 1341, 20),
('05', 'buku', 'Lusin', 15000, 200, 20);

-- --------------------------------------------------------

--
-- Struktur dari tabel `beli`
--

CREATE TABLE `beli` (
  `no_beli` int(10) NOT NULL,
  `kd_pms` char(6) DEFAULT NULL,
  `tgl_beli` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `beli`
--

INSERT INTO `beli` (`no_beli`, `kd_pms`, `tgl_beli`) VALUES
(1, '02', '2023-07-01'),
(2, '03', '2023-07-02'),
(3, '01', '2023-07-02'),
(4, '03', '2023-07-02');

-- --------------------------------------------------------

--
-- Struktur dari tabel `dbeli`
--

CREATE TABLE `dbeli` (
  `no_beli` int(10) NOT NULL,
  `kd_brg` char(6) NOT NULL,
  `harga_beli` float DEFAULT NULL,
  `jml_beli` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `dbeli`
--

INSERT INTO `dbeli` (`no_beli`, `kd_brg`, `harga_beli`, `jml_beli`) VALUES
(1, '01', 1000000, 2),
(2, '02', 15000000, 2),
(2, '03', 25000000, 7),
(2, '04', 2500000, 5),
(2, '06', 650000, 4),
(3, '05', 15000, 8),
(4, '05', 15000, 7);

-- --------------------------------------------------------

--
-- Struktur dari tabel `djual`
--

CREATE TABLE `djual` (
  `no_jual` int(10) NOT NULL,
  `kd_brg` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `harga_jual` float DEFAULT NULL,
  `jml_jual` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `djual`
--

INSERT INTO `djual` (`no_jual`, `kd_brg`, `harga_jual`, `jml_jual`) VALUES
(8, '02', 15000000, 2),
(8, '03', 25000000, 2),
(8, '04', 2500000, 3),
(8, '06', 650000, 1),
(9, '01', 1000000, 1),
(9, '02', 15000000, 2),
(9, '03', 25000000, 2),
(9, '04', 2500000, 3),
(9, '06', 650000, 1),
(10, '01', 1000000, 5),
(10, '02', 15000000, 2),
(10, '03', 25000000, 3),
(10, '04', 2500000, 4),
(11, '01', 1000000, 5),
(12, '01', 1000000, 5),
(12, '02', 15000000, 3),
(12, '05', 15000, 3),
(13, '05', 15000, 7);

-- --------------------------------------------------------

--
-- Struktur dari tabel `jual`
--

CREATE TABLE `jual` (
  `no_jual` int(10) NOT NULL,
  `kd_kons` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `tgl_jual` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `jual`
--

INSERT INTO `jual` (`no_jual`, `kd_kons`, `tgl_jual`) VALUES
(8, '06', '2023-06-16'),
(9, '03', '2023-06-16'),
(10, '04', '2023-06-23'),
(11, '02', '2023-07-01'),
(12, '04', '2023-07-02'),
(13, '04', '2023-07-02');

-- --------------------------------------------------------

--
-- Struktur dari tabel `konsumen`
--

CREATE TABLE `konsumen` (
  `kd_kons` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nm_kons` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `alm_kons` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `kota_kons` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `kd_pos` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `konsumen`
--

INSERT INTO `konsumen` (`kd_kons`, `nm_kons`, `alm_kons`, `kota_kons`, `kd_pos`, `phone`, `email`) VALUES
('01', 'Fidela Azzahra', 'Sri Rejeki Selatan 8', 'Semarang', '50149', '8262', 'fidela@gmail.com'),
('03', 'Qanitah', 'Sri Rejeki Selatan', 'Semarang', '50149', '0004', 'qani@gmail.com'),
('04', 'Qani', 'Sri Rejeki', 'Semarang', '50149', '4000', 'qani@gmail.com');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pemasok`
--

CREATE TABLE `pemasok` (
  `kd_pms` varchar(6) NOT NULL,
  `nm_pms` varchar(30) DEFAULT NULL,
  `nm_brg` varchar(30) DEFAULT NULL,
  `kota_pms` varchar(20) DEFAULT NULL,
  `kd_pos` varchar(5) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `pemasok`
--

INSERT INTO `pemasok` (`kd_pms`, `nm_pms`, `nm_brg`, `kota_pms`, `kd_pos`, `phone`) VALUES
('01', 'Thoma', 'Genesis Krystal', 'Inazuma City', '42626', '8765'),
('02', 'Sara', 'Boneka Shogun', 'Inazuma City', '42626', '8765'),
('03', 'Yae Miko', 'Boneka', 'Inazuma City', '42626', '9999'),
('04', 'Diluc', 'Asus TUF', 'Monsdstat', '00001', '2020');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id` int(4) NOT NULL,
  `user_id` varchar(10) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `password` varchar(254) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id`, `user_id`, `name`, `password`) VALUES
(1, '12345', 'alcina', '15fc33b24e2d528ffc5603547b9e6d27'),
(2, '12345', 'fidel', 'fa1776fe544c44fad1cf2bec71a14464'),
(3, '12345', 'admin', '21232f297a57a5a743894a0e4a801fc3');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kd_brg`);

--
-- Indeks untuk tabel `beli`
--
ALTER TABLE `beli`
  ADD PRIMARY KEY (`no_beli`);

--
-- Indeks untuk tabel `dbeli`
--
ALTER TABLE `dbeli`
  ADD PRIMARY KEY (`no_beli`,`kd_brg`);

--
-- Indeks untuk tabel `djual`
--
ALTER TABLE `djual`
  ADD PRIMARY KEY (`no_jual`,`kd_brg`);

--
-- Indeks untuk tabel `jual`
--
ALTER TABLE `jual`
  ADD PRIMARY KEY (`no_jual`);

--
-- Indeks untuk tabel `konsumen`
--
ALTER TABLE `konsumen`
  ADD PRIMARY KEY (`kd_kons`);

--
-- Indeks untuk tabel `pemasok`
--
ALTER TABLE `pemasok`
  ADD PRIMARY KEY (`kd_pms`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `user`
--
ALTER TABLE `user`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
