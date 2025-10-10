-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 10, 2025 at 03:57 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library`
--

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `book_id` varchar(15) NOT NULL,
  `title` text DEFAULT NULL,
  `author` char(30) DEFAULT NULL,
  `genre` char(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`book_id`, `title`, `author`, `genre`) VALUES
('B001', 'The People We Pretend to Be', 'Lucy Khan', 'Fiction'),
('B002', 'Chronos and the Cell', 'Linda Mugambi', 'Science'),
('B003', 'The Quiet Power of Ordinary People', 'Brian Mugambi', 'Non-fiction'),
('B004', 'Beyond the Numbers: What Data Can\'t Tell Us', 'Jane Ali', 'Non-fiction'),
('B005', 'The Library of Unwritten Letters', 'Jane Otieno', 'Fiction'),
('B006', 'The Gravity of Silence', 'Carlos Smith', 'Science'),
('B007', 'The Art of Being Almost', 'Aisha Khan', 'Fiction'),
('B008', 'Walking Backwards into the Future', 'Mary Karanja', 'Non-fiction'),
('B009', 'The Language of Places: How Geography Wries Identity', 'Amira Wei', 'Non-fiction'),
('B010', 'The Botanist\'s Paradox', 'Lucy Murithi', 'Science'),
('B011', 'The Language of Light', 'Jane Khan', 'Science'),
('B012', 'Beneath the Marble: Secrets of the Old Republic', 'Aisha Otieno', 'History'),
('B013', 'What the Wind Forgot', 'Ali Karanja', 'Fiction'),
('B014', 'The Invisible Thread: Connections That Shape the World', 'Chen Ouma', 'Nom-fiction'),
('B015', 'The Last Experiment of Dr. Voss', 'Mary Muli', 'Science'),
('B016', 'The History of Mistakes: Learning from What Went Wrong', 'Sara Karanja', 'Non-fiction'),
('B017', 'How to Vanish in a Small Town', 'Omar Kim', 'Fiction'),
('B018', 'Beneath the Orange Sky', 'Mary Murithi', 'Fiction'),
('B019', 'The Equation That Changed Tomorrow', 'Mark Njoroge', 'Science'),
('B020', 'In the Shadow of the Particle', 'Sam Njoroge', 'Science'),
('B021', 'How the Stars Forgot Us', 'Tom Achieng', 'Science'),
('B022', 'The Empire That Never Slept', 'Carlos Njoroge', 'History'),
('B023', 'Echoes from the Iron Road', 'Sara Ouma', 'History'),
('B024', 'The Art of Listening to Silence', 'Amira Njoroge', 'Non-fiction'),
('B025', 'Timekeepers: How We Learned to Measure the Moment', 'Omar Wei', 'Non-fiction'),
('B026', 'The Silence After Goodbye', 'John Wang', 'Fiction'),
('B027', 'Thinking in Circles: How Patterns Shape Our Lives', 'Lucy Wang', 'Non-fiction'),
('B028', 'The Mapmaker\'s War', 'Brian Karanja', 'History'),
('B029', 'Blueprints for a Broken Universe', 'Jane Mwangi', 'Science'),
('B030', 'Rain in the Middle of August', 'Paul Kim', 'Fiction');

-- --------------------------------------------------------

--
-- Table structure for table `borrow_records`
--

CREATE TABLE `borrow_records` (
  `record_id` varchar(20) NOT NULL,
  `member_id` varchar(15) NOT NULL,
  `book_id` varchar(15) NOT NULL,
  `borrow_date` date NOT NULL,
  `return_date` date NOT NULL,
  `days_borrowed` int(11) GENERATED ALWAYS AS (to_days(`return_date`) - to_days(`borrow_date`)) VIRTUAL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `borrow_records`
--

INSERT INTO `borrow_records` (`record_id`, `member_id`, `book_id`, `borrow_date`, `return_date`) VALUES
('R0001', 'M041', 'B007', '2025-12-20', '2025-12-30'),
('R0002', 'M017', 'B026', '2025-02-04', '2025-02-14'),
('R0003', 'M020', 'B017', '2025-10-09', '2025-10-20'),
('R0004', 'M050', 'B001', '2025-02-28', '2025-03-22'),
('R0005', 'M022', 'B011', '2025-07-22', '2025-08-15'),
('R0006', 'M037', 'B020', '2025-01-15', '2025-02-11'),
('R0007', 'M012', 'B007', '2025-06-19', '2025-07-16'),
('R0008', 'M022', 'B030', '2025-12-11', '2025-12-27'),
('R0009', 'M031', 'B026', '2025-01-19', '2025-02-09'),
('R0010', 'M009', 'B016', '2025-03-21', '2025-04-11'),
('R0011', 'M030', 'B029', '2025-10-02', '2025-10-31'),
('R0012', 'M022', 'B015', '2025-07-31', '2025-08-30'),
('R0013', 'M048', 'B002', '2025-09-24', '2025-10-04'),
('R0014', 'M037', 'B024', '2025-12-14', '2025-12-25'),
('R0015', 'M036', 'B018', '2025-01-10', '2025-01-17'),
('R0016', 'M037', 'B022', '2025-06-19', '2025-07-15'),
('R0017', 'M023', 'B003', '2025-03-18', '2025-03-27'),
('R0018', 'M002', 'B011', '2025-07-12', '2025-08-02'),
('R0019', 'M035', 'B027', '2025-03-26', '2025-04-14'),
('R0020', 'M039', 'B013', '2025-04-06', '2025-04-26'),
('R0021', 'M008', 'B028', '2025-06-07', '2025-06-30'),
('R0022', 'M021', 'B023', '2025-01-17', '2025-01-26'),
('R0023', 'M027', 'B003', '2025-09-12', '2025-10-11'),
('R0024', 'M016', 'B012', '2025-08-20', '2025-09-16'),
('R0025', 'M020', 'B007', '2025-09-26', '2025-10-06'),
('R0026', 'M012', 'B004', '2025-07-11', '2025-08-06'),
('R0027', 'M003', 'B025', '2025-04-14', '2025-05-14'),
('R0028', 'M006', 'B015', '2025-09-17', '2025-09-18'),
('R0029', 'M031', 'B029', '2025-09-14', '2025-09-19'),
('R0030', 'M013', 'B011', '2025-09-05', '2025-09-29'),
('R0031', 'M034', 'B010', '2025-06-17', '2025-06-22'),
('R0032', 'M050', 'B028', '2025-02-20', '2025-03-19'),
('R0033', 'M034', 'B022', '2025-03-09', '2025-03-16'),
('R0034', 'M047', 'B002', '2025-08-24', '2025-09-06'),
('R0035', 'M046', 'B028', '2025-09-25', '2025-09-29'),
('R0036', 'M001', 'B010', '2025-01-23', '2025-02-14'),
('R0037', 'M039', 'B028', '2025-02-14', '2025-02-25'),
('R0038', 'M030', 'B029', '2025-01-24', '2025-01-28'),
('R0039', 'M034', 'B025', '2025-12-04', '2025-12-28'),
('R0040', 'M020', 'B001', '2025-10-23', '2025-11-17'),
('R0041', 'M020', 'B021', '2025-04-18', '2025-04-29'),
('R0042', 'M013', 'B007', '2025-05-27', '2025-06-05'),
('R0043', 'M035', 'B011', '2025-08-14', '2025-09-05'),
('R0044', 'M008', 'B009', '2025-03-18', '2025-03-24'),
('R0045', 'M018', 'B004', '2025-01-08', '2025-01-28'),
('R0046', 'M036', 'B017', '2025-02-22', '2025-03-01'),
('R0047', 'M035', 'B012', '2025-10-09', '2025-10-20'),
('R0048', 'M018', 'B005', '2025-12-27', '2026-01-22'),
('R0049', 'M019', 'B019', '2025-05-06', '2025-05-12'),
('R0050', 'M005', 'B010', '2025-11-10', '2025-12-09'),
('R0051', 'M022', 'B018', '2025-08-16', '2025-08-18'),
('R0052', 'M009', 'B001', '2025-08-30', '2025-09-28'),
('R0053', 'M044', 'B002', '2025-02-24', '2025-03-09'),
('R0054', 'M004', 'B003', '2025-08-07', '2025-08-20'),
('R0055', 'M012', 'B023', '2025-11-02', '2025-12-01'),
('R0056', 'M038', 'B021', '2025-12-20', '2026-01-12'),
('R0057', 'M029', 'B020', '2025-07-02', '2025-07-12'),
('R0058', 'M022', 'B027', '2025-04-12', '2025-05-05'),
('R0059', 'M049', 'B022', '2025-10-01', '2025-10-04'),
('R0060', 'M049', 'B025', '2025-08-27', '2025-09-15'),
('R0061', 'M009', 'B003', '2025-12-24', '2026-01-08'),
('R0062', 'M007', 'B015', '2025-06-08', '2025-06-30'),
('R0063', 'M042', 'B023', '2025-08-27', '2025-09-12'),
('R0064', 'M015', 'B008', '2025-03-15', '2025-04-12'),
('R0065', 'M040', 'B009', '2025-07-06', '2025-06-22'),
('R0066', 'M012', 'B023', '2025-12-07', '2025-12-18'),
('R0067', 'M021', 'B015', '2025-09-07', '2025-09-08'),
('R0068', 'M005', 'B016', '2025-02-27', '2025-03-11'),
('R0069', 'M031', 'B015', '2025-03-29', '2025-04-08'),
('R0070', 'M050', 'B012', '2025-11-06', '2025-11-25'),
('R0071', 'M009', 'B017', '2025-02-22', '2025-03-07'),
('R0072', 'M046', 'B026', '2025-02-24', '2025-06-22'),
('R0073', 'M036', 'B021', '2025-02-06', '2025-03-04'),
('R0074', 'M020', 'B018', '2025-08-25', '2025-09-07'),
('R0075', 'M038', 'B014', '2025-05-03', '2025-05-23'),
('R0076', 'M016', 'B018', '2025-03-23', '2025-04-08'),
('R0077', 'M025', 'B003', '2025-06-24', '2025-07-01'),
('R0078', 'M045', 'B019', '2025-08-09', '2025-09-04'),
('R0079', 'M028', 'B019', '2025-06-25', '2025-07-13'),
('R0080', 'M045', 'B008', '2025-03-30', '2025-04-21'),
('R0081', 'M043', 'B003', '2025-12-31', '2026-01-19'),
('R0082', 'M032', 'B004', '2025-10-31', '2025-11-20'),
('R0083', 'M030', 'B011', '2025-10-12', '2025-10-16'),
('R0084', 'M029', 'B006', '2025-06-07', '2025-06-20'),
('R0085', 'M005', 'B020', '2025-02-25', '2025-03-19'),
('R0086', 'M039', 'B025', '2025-07-14', '2025-07-25'),
('R0087', 'M032', 'B021', '2025-12-05', '2025-12-07'),
('R0088', 'M006', 'B007', '2025-03-13', '2025-04-11'),
('R0089', 'M038', 'B029', '2025-11-22', '2025-12-18'),
('R0090', 'M001', 'B025', '2025-03-17', '2025-04-08'),
('R0091', 'M010', 'B015', '2025-09-30', '2025-10-10'),
('R0092', 'M025', 'B020', '2025-10-23', '2025-11-07'),
('R0093', 'M013', 'B007', '2025-12-15', '2026-01-13'),
('R0094', 'M021', 'B010', '2025-10-19', '2025-11-14'),
('R0095', 'M044', 'B010', '2025-08-11', '2025-09-03'),
('R0096', 'M023', 'B003', '2025-10-26', '2025-10-27'),
('R0097', 'M040', 'B017', '2025-07-26', '2025-08-23'),
('R0098', 'M009', 'B028', '2025-05-31', '2025-06-02'),
('R0099', 'M024', 'B029', '2025-10-09', '2025-10-11'),
('R0100', 'M001', 'B023', '2025-10-14', '2025-11-10');

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE `members` (
  `member_id` varchar(5) NOT NULL,
  `first_name` char(15) DEFAULT NULL,
  `last_name` char(15) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `members`
--

INSERT INTO `members` (`member_id`, `first_name`, `last_name`, `email`) VALUES
('M001', 'James', 'Smith', 'james1@library.com'),
('M002', 'David', 'Wei', 'david2@library.com'),
('M003', 'Lucy', 'Ndegwa', 'lucy3@library.com'),
('M004', 'Chen', 'Wei', 'chen4@library.com'),
('M005', 'Lucy', 'Ali', 'lucy5@library.com'),
('M006', 'Sam', 'Wei', 'sam6@library.com'),
('M007', 'Brian', 'Smith', 'brian7@library.com'),
('M008', 'Omar', 'Achieng', 'omar8@library.com'),
('M009', 'Carlos', 'Mworia', 'carlos9@library.com'),
('M010', 'Fatima', 'Mworia', 'fatima10@gmail.com'),
('M011', 'Ali', 'Achieng', 'ali11@library.com'),
('M012', 'Mary', 'Ali', 'mary12@library.com'),
('M013', 'James', 'Murithi', 'james13@library.com'),
('M014', 'Omar', 'Mugambi', 'omar14@library.com'),
('M015', 'Carlos', 'Ndegwa', 'carlos15@library.com'),
('M016', 'Sam', 'Mwangi', 'sam16@library.com'),
('M017', 'Lucy', 'Doe', 'lucy17@library.com'),
('M018', 'Amira', 'Mworia', 'amira18@library.com'),
('M019', 'Njeri', 'Kim', 'njeri19@library.com'),
('M020', 'Lucy', 'Khan', 'lucy20@library.com'),
('M021', 'Tom', 'Mwangi', 'tom21@library.com'),
('M022', 'Omar', 'Ali', 'omar22@library.com'),
('M023', 'Omar', 'Wang', 'omar23@library.com'),
('M024', 'Ali', 'Wang', 'ali24@library.com'),
('M025', 'Omar', 'Mworia', 'omar25@library.com'),
('M026', 'Fatima', 'Otieno', 'fatima26@library.com'),
('M027', 'Ali', 'Achieng', 'ali27@library.com'),
('M028', 'Omar', 'Ndegwa', 'omar28@library.com'),
('M029', 'Ruth', 'Achieng', 'ruth29@library.com'),
('M030', 'Brian', 'Ali', 'brian30@library.com'),
('M031', 'Grace', 'Mugambi', 'grace31@library.com'),
('M032', 'Grace', 'Smith', 'grace32@library.com'),
('M033', 'James', 'Ali', 'james33@library.com'),
('M034', 'Aisha', 'Murithi', 'aisha34@library.com'),
('M035', 'Kim', 'Ruiz', 'kim35@library.com'),
('M036', 'Amira', 'Ruiz', 'amira36@library.com'),
('M037', 'Amira', 'Smith', 'amira37@library.com'),
('M038', 'Sam', 'Wei', 'sam38@library.com'),
('M039', 'Chen', 'Mworia', 'chen39@library.com'),
('M040', 'Chen', 'Ali', 'chen40@library.com'),
('M041', 'Mary', 'Ruiz', 'mary41@library.com'),
('M042', 'David', 'Doe', 'david42@library.com'),
('M043', 'Brian', 'Muli', 'brian43@library.com'),
('M044', 'John', 'Mworia', 'john44@library.com'),
('M045', 'Mark', 'Ouma', 'mark45@library.com'),
('M046', 'Paul', 'Doe', 'paul46@library.com'),
('M047', 'Brian', 'Ouma', 'brian47@library.com'),
('M048', 'Linda', 'Kim', 'linda48@library.com'),
('M049', 'Njeri', 'Ouma', 'njeri49@library.com'),
('M050', 'Lucy', 'Karanja', 'lucy50@library.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`book_id`);

--
-- Indexes for table `borrow_records`
--
ALTER TABLE `borrow_records`
  ADD PRIMARY KEY (`record_id`),
  ADD KEY `member_id` (`member_id`),
  ADD KEY `book_id` (`book_id`);

--
-- Indexes for table `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`member_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
