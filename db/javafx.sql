-- TUTORIAL IMPORT DATABASE DI PHP MY ADMIN
-- 1. Buka XAMPP / Laragon, lalu jalankan Apache dan MySQL.
-- 2. Buka browser dan kunjungi: http://localhost/phpmyadmin
-- 3. Klik menu "Import" di bagian atas.
-- 4. Klik "Choose File" dan pilih file ini (javafx.sql).
-- 5. Klik tombol "Go" dan tunggu proses import selesai.
-- 6. Setelah selesai, database bernama `javafx` akan muncul
--    dan sudah terisi data tabel `users` dan `matakuliah`.

-- Buat database dan gunakan
CREATE DATABASE IF NOT EXISTS javafx;
USE javafx;

-- Buat tabel matakuliah
CREATE TABLE matakuliah (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  kode_matakuliah VARCHAR(20),
  nama_matakuliah VARCHAR(100),
  sks INT
);

-- Isi data matakuliah (Opsional)
INSERT INTO matakuliah (id, kode_matakuliah, nama_matakuliah, sks) VALUES
(1, 'A0321', 'PEMROGRAMAN JAVA', 3),
(2, 'A0345', 'PENGEMBANGAN SISTEM INFORMASI', 3),
(3, 'A0124', 'PRAKTIKUM PEMROGRAMAN JAVA', 2),
(5, 'A0587', 'BAHASA INDONESIA', 2),
(6, 'A0213', 'SISTEM OPERASI JARINGAN', 3);

-- Buat tabel users
CREATE TABLE users (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  nama_lengkap VARCHAR(100),
  nim VARCHAR(50),
  no_hp VARCHAR(50),
  email VARCHAR(100),
  jenis_kelamin VARCHAR(10)
);

-- Isi data users (Opsional)
INSERT INTO users (id, username, password, nama_lengkap, nim, no_hp, email, jenis_kelamin) VALUES
(1, 'admin', 'admin123', 'Admin Ganteng', '200000000', '082134589324', 'admin@gmail.com', 'Laki-laki'),
(2, 'Rio', '123', 'Rio Kurniawan', '232520037', '082134589342', 'Rayo@gmail.com', 'Laki-laki'),
(3, 'Raya', '123', 'Raya Cumi', '232420034', '082345167887', 'Raya@gmail.com', 'Perempuan'),
(4, 'Rayo', '123', 'Rayo Suka Tidur', '232520037', '082134589342', 'Rayo@gmail.com', 'Laki-laki'),
(6, 'RioKrnwan', '123', 'Rio Kurniawan', '232520037', '082134589342', 'Rayo@gmail.com', 'Laki-laki');
