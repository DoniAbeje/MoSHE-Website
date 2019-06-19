-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 16, 2019 at 02:30 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `moshe_website`
--

-- --------------------------------------------------------

--
-- Table structure for table `document`
--

CREATE TABLE `document` (
  `id` bigint(20) NOT NULL,
  `date_time` date DEFAULT NULL,
  `description` text,
  `language` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `document_type_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `document`
--

INSERT INTO `document` (`id`, `date_time`, `description`, `language`, `name`, `title`, `document_type_id`) VALUES
(23, '2019-05-06', 'የሥራ አፈጻጸም ግምገማ ውጤት ጋዜጣዊ መግለጫ፤ የሳይንስና ከፍተኛ ትምህርት ሚኒስቴር በአዲስ መልክ በአዋጅ ቁጥር 1097/2011 ከተቋቋመበት ጊዜ ጀምሮ በሥራ ዕቅድ ያከናወናቸውን የትግበራ ውጤት ግምገማ አስመልክቶ ጋዜጣዊ መግለጫ መስጠቱ ይታወቃል፡፡ በክብርት ሚኒስትር ፕሮፌሰር ሂሩት ወልደማርያም በተሰጠው ጋዜጣዊ መግለጫ ከተለያዩ ተጠሪ ተቋማት እና ከባለድርሻ አካላት ጋር ተገምግመው በጥንካሬና በክፍተት የተለዩ ውጤቶች እንዲሁም የወደፊት የትኩረት አቅጣጫተካተዋል፡፡ የክብርት ሚኒስትሯን ሙሉ መግለጫ ከዚህ ሊንክ ያግኙ ', 0, '1557136312499.pdf', 'የሳይንስና ከፍተኛ ትምህርት ሚኒስቴር ከተቋቋመበት ጊዜ ጀምሮ በሥራ ዕቅድ ያከናወነው የትግበራ ውጤት', 9),
(24, '2019-05-06', '2011 E.C NIMEI Placement', 0, '1557136473200.pdf', '2011 E.C NIMEI Placement', 7),
(25, '2019-05-06', '2011 E.C NIMEI Interview Result and Final Pass/Fail Status', 0, '1557136866013.pdf', '2011 E.C NIMEI Interview Result', 7);

-- --------------------------------------------------------

--
-- Table structure for table `document_type`
--

CREATE TABLE `document_type` (
  `id` bigint(20) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `language` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `document_type`
--

INSERT INTO `document_type` (`id`, `title`, `language`) VALUES
(2, 'Policy', 0),
(5, 'Guidline', 0),
(6, 'ፖሊሲ', 1),
(7, 'Other Documents', 0),
(8, 'Standard', 0),
(9, 'ሌሎች ሰነዶች', 1);

-- --------------------------------------------------------

--
-- Table structure for table `general_inforamtion`
--

CREATE TABLE `general_inforamtion` (
  `id` bigint(20) NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  `office_image` varchar(255) DEFAULT NULL,
  `amharic_description` text,
  `english_description` text,
  `structure_image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `general_inforamtion`
--

INSERT INTO `general_inforamtion` (`id`, `location`, `office_image`, `amharic_description`, `english_description`, `structure_image`) VALUES
(1, 'https://maps.google.com/maps?q=8.984779%2C38.784477&t=&z=13&ie=UTF8&iwloc=&output=embed', '1557138563733.png', '<h1 class=\"ql-align-center\">የሳይንስ እና ከፍተኛ ትምህርት ሚኒስቴር</h1><p class=\"ql-align-center\"><br></p><p class=\"ql-align-center\">የሳይንስና የከፍተኛ ትምህርት ሚኒስቴር, በአዋጅ ቁጥር 1097/2018 እ.ኤ.አ. ጥቅምት 2018 የተቋቋመው የሳይንስና የከፍተኛ ትምህርት እንዲሁም የቴክኒክና ሙያ ትምህርትና ስልጠና (ቴ/ሙ/ት/ሥ) በኢትዮጵያ እንዲመራ ለማድረግ ነው</p><p class=\"ql-align-justify\"><br></p><p class=\"ql-align-justify\">በከፍተኛ ትምህርት ተቋሞቻችን የሚኖረን የከፍተኛው እርከን አመራር በተቋማቱ አጠቃላይ አፈፃፀም ላይ የላቀና ጉልህ የሆነ ልዩነት ማስመዘገብ ይችላል፡፡&nbsp;በመሆኑም&nbsp;በከፍተኛ ትምህርት ተቋማት&nbsp;በኩል ልናሳካው ላሰብነው ማህበራዊና ኢኮኖሚያዊ ልማት ግቦቻችን ወሳኝ የሆነውን ይህንን ጉዳይ በጥንቃቄ መፈፀም ይኖርብናል፡፡ በባለፉት ዓመታት የከፍተኛ ትምህርትን ተደራሽነት፣ ጥራት፣ ተገቢነትና ፍትሃዊነትን ለማሻሸል በአመራሩ አማካይነት&nbsp;ያስመዘገብነውን&nbsp;ውጤት&nbsp;ወደ ላቀ ደረጃ ለማሸጋገር&nbsp;እንዲቻልና በከፍተኛው እርከን የሚሰማሩትን ግልፀኝነትና ብቃትን ማዕከል ባደረገ&nbsp;መስፈርት መምረጥ እንደሚገባ ስለታመነበት ከሚመለከታቸው ጋር መግባባት ተፈጥሯል፡፡</p><p class=\"ql-align-justify\"><br></p><p class=\"ql-align-justify\"><br></p><p class=\"ql-align-justify\">ይህን ማኑዋል ወደ ሥራ በማስገባት&nbsp;የከፍተኛ ትምህርት ተቋማትን በብቁ፣ ተወዳዳሪና ውጤታማ ግለሰቦች&nbsp;እንዲመሩ በማድረግ የትምህርት ጥራትና ተገቢነት በማረጋገጥና ተቋማቱ የልህቀት ማዕከል ሆነው ለአገራዊው የሰላም፣ልማትና ዲሞክራሲያዊ ሥርዓት ግንባታ የበኩላቸውን&nbsp;እንዲወጡ ለማስቻል መስራት ያስፈልጋል፡፡&nbsp;</p><p class=\"ql-align-justify\">በከፍተኛ ትምህርት ተቋሞቻችን የሚኖረን የከፍተኛው እርከን አመራር በተቋማቱ አጠቃላይ አፈፃፀም ላይ የላቀና ጉልህ የሆነ ልዩነት ማስመዘገብ ይችላል፡፡&nbsp;በመሆኑም&nbsp;በከፍተኛ ትምህርት ተቋማት&nbsp;በኩል ልናሳካው ላሰብነው ማህበራዊና ኢኮኖሚያዊ ልማት ግቦቻችን ወሳኝ የሆነውን ይህንን ጉዳይ በጥንቃቄ መፈፀም ይኖርብናል፡፡ በባለፉት ዓመታት የከፍተኛ ትምህርትን ተደራሽነት፣ ጥራት፣ ተገቢነትና ፍትሃዊነትን ለማሻሸል በአመራሩ አማካይነት&nbsp;ያስመዘገብነውን&nbsp;ውጤት&nbsp;ወደ ላቀ ደረጃ ለማሸጋገር&nbsp;እንዲቻልና በከፍተኛው እርከን የሚሰማሩትን ግልፀኝነትና ብቃትን ማዕከል ባደረገ&nbsp;መስፈርት መምረጥ እንደሚገባ ስለታመነበት ከሚመለከታቸው ጋር መግባባት ተፈጥሯል፡፡</p><p class=\"ql-align-justify\"><br></p><p class=\"ql-align-justify\"><br></p><p class=\"ql-align-justify\">ይህን ማኑዋል ወደ ሥራ በማስገባት&nbsp;የከፍተኛ ትምህርት ተቋማትን በብቁ፣ ተወዳዳሪና ውጤታማ ግለሰቦች&nbsp;እንዲመሩ በማድረግ የትምህርት ጥራትና ተገቢነት በማረጋገጥና ተቋማቱ የልህቀት ማዕከል ሆነው ለአገራዊው የሰላም፣ልማትና ዲሞክራሲያዊ ሥርዓት ግንባታ የበኩላቸውን&nbsp;እንዲወጡ ለማስቻል መስራት ያስፈልጋል፡፡&nbsp;</p>', '<h1><br></h1><h1 class=\"ql-align-center\">Ministry of Science and Higher Education</h1><p><br></p><blockquote class=\"ql-align-center\"><span style=\"color: rgb(68, 68, 68);\">The Ministry of Science and Higher Education (MoSHE),established by proclamation number 1097/2018 in October 2018, is responsible to lead the development of science, higher education as well as the technical and vocational education and training (TVET) in Ethiopia.</span></blockquote><p><br></p><h2 class=\"ql-align-center\">Mission</h2><p><br></p><p class=\"ql-align-justify\"><span style=\"color: rgb(68, 68, 68);\">The Ministry of Science and Higher Education (MoSHE),established by proclamation number 1097/2018 in October 2018, is responsible to lead the development of science, higher education as well as the technical and vocational education and training (TVET) in Ethiopia.The Ministry of Science and Higher Education (MoSHE),established by proclamation number 1097/2018 in October 2018, is responsible to lead the development of science, higher education as well as the technical and vocational education and training (TVET) in Ethiopia.</span></p><h2><br></h2><h2 class=\"ql-align-center\">Vision</h2><p><br></p><p class=\"ql-align-justify\"><span style=\"color: rgb(68, 68, 68);\">The Ministry of Science and Higher Education (MoSHE),established by proclamation number 1097/2018 in October 2018, is responsible to lead the development of science, higher education as well as the technical and vocational education and training (TVET) in Ethiopia.The Ministry of Science and Higher Education (MoSHE),established by proclamation number 1097/2018 in October 2018, is responsible to lead the development of science, higher education as well as the technical and vocational education and training (TVET) in Ethiopia.</span></p><h2><br></h2><h2 class=\"ql-align-center\">Values</h2><p><br></p><p class=\"ql-align-justify\"><span style=\"color: rgb(68, 68, 68);\">The Ministry of Science and Higher Education (MoSHE),established by proclamation number 1097/2018 in October 2018, is responsible to lead the development of science, higher education as well as the technical and vocational education and training (TVET) in Ethiopia.The Ministry of Science and Higher Education (MoSHE),established by proclamation number 1097/2018 in October 2018, is responsible to lead the development of science, higher education as well as the technical and vocational education and training (TVET) in Ethiopia.</span></p><p class=\"ql-align-justify\"><br></p><p class=\"ql-align-justify\"><span style=\"color: rgb(68, 68, 68);\">The Ministry of Science and Higher Education (MoSHE),established by proclamation number 1097/2018 in October 2018, is responsible to lead the development of science, higher education as well as the technical and vocational education and training (TVET) in Ethiopia.</span></p>', '1557139386587.png');

-- --------------------------------------------------------

--
-- Table structure for table `job`
--

CREATE TABLE `job` (
  `id` bigint(20) NOT NULL,
  `date_time` date DEFAULT NULL,
  `description` text,
  `language` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `job`
--

INSERT INTO `job` (`id`, `date_time`, `description`, `language`, `title`) VALUES
(1, '2019-04-09', 'The Ministry of Higher Education is responsible for the design, implementation, monitoring and evaluation of educational legislation, policies and programs in Ethiopia. We provide the structures, human resources, budget and administrative and management support to ensure that the quality of service delivery is maintained at a high level.', 0, 'Minister'),
(3, '2019-04-09', 'These offices have a liaison role between the Minister of State and other sections within the Department with other departments, public bodies, education partners and the public.', 0, 'State Minister'),
(4, '2019-04-09', 'የከፍተኛ ትምህርት ሚኒስቴር በኢትዮጵያ ውስጥ የትምህርት ሕግ፣ ፖሊሲ፣ የመርሃግብር ዲዛይን እና ትግበራ የመከታተል እና የመገምገም ኃላፊነት አለበት። በአገልግሎቱ ጥራት ያለው አገልግሎትና የትምህርት አሰጣጥ በተገቢ ሁኔታ እንዲቀጥል ለማረጋገጥ መዋቅሮችን፣ የሰብአዊ ሀብትን፣ የበጀት እና አስተዳደራዊ እና አስተዳድር ድጋፍን ከፍተኛ ደረጃ ለማድረስ ይሰራል።', 1, 'ሚኒስተር'),
(5, '2019-04-09', 'እነዚህ ጽሕፈት ቤቶች በስቴቱ ሚኒስትር እና በሌሎች ክፍሎች ውስጥ ከመንግስት አካላት፣ ከትምህርት አጋሮች እና ከሕዝብ ጋር በጋራ ይሰራሉ።', 1, 'ሚኒስትር ዴታ');

-- --------------------------------------------------------

--
-- Table structure for table `link`
--

CREATE TABLE `link` (
  `id` bigint(20) NOT NULL,
  `link` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `link`
--

INSERT INTO `link` (`id`, `link`, `title`) VALUES
(2, 'http://facebook.comp', 'ADDIS ABABA UNIVERSITY'),
(3, 'http://facebook.com', 'HAWASSA UNIVERSIY'),
(4, 'http://facebook.com', 'MEKEL UNIVERSIY'),
(5, 'http://facebook.com', 'JIMMA UNIVERSIY'),
(6, 'http://facebook.com', 'BAHIR DAR UNIVERSIY'),
(7, 'http://facebook.com', 'GONDER UNIVERSIY');

-- --------------------------------------------------------

--
-- Table structure for table `news`
--

CREATE TABLE `news` (
  `id` bigint(20) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `content` text,
  `date_time` date DEFAULT NULL,
  `description` text,
  `language` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `job_id` bigint(20) DEFAULT NULL,
  `document_id` bigint(20) DEFAULT NULL,
  `is_video` bit(1) NOT NULL,
  `video` varchar(255) DEFAULT NULL,
  `video_link` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `news`
--

INSERT INTO `news` (`id`, `author`, `content`, `date_time`, `description`, `language`, `title`, `job_id`, `document_id`, `is_video`, `video`, `video_link`) VALUES
(8, 'Admin', NULL, '2019-05-06', 'በሳይንስና ከፍተኛ ትምህርት ሚኒስቴር እና በከፍተኛ ትምህርት ስትራቴጂ ማዕከል በተዘጋጀው መድረክ የዩኒቨርሲቲ ፕሬዚዳንቶች፣ የቦርድ አባላት፣ የተለያዩ ባለድርሻ ካላት እየተወያዩ ይገኛሉ፡፡', 1, 'በዩኒቨርሲቲዎች Differentiation እና የከፍተኛ ትምህርት ስርዓተ - ትምህርት /Curriculum/ ክለሳ ዙሪያ በአዲስ አበባ ከተማ ውይይት እየተደረገ ይገኛል፡፡', NULL, NULL, b'0', NULL, NULL),
(9, 'Admin', NULL, '2019-05-06', 'ክብርት ሚኒስትር ፕሮፌሰር ሂሩት ወልደማርያም በሥራ እቅድ አፈጻጸም ግምገማ የተገኙ ውጤቶች እና የታዩ ክፍተቶችን አስመልክቶ የተለያዩ የሚዲያ ተቋማት በተገኙበት ጋዜጣዊ መግለጫ ሰጥተዋል፡፡', 1, 'የሳይንስና ከፍተኛ ትምህርት ሚኒስቴር በሥራ ዕቅድ አፈጻጸም የገመገመውን የትግበራ ውጤት አስመልክቶ ጋዜጣዊ መግለጫ ሰጠ', NULL, NULL, b'0', NULL, NULL),
(10, 'Admin', NULL, '2019-05-06', 'የስርዓተ - ትምህርት ክለሳው ዋና ዓላማ በዩኒቨርሲቲዎች የሚሰጡት ትምህርቶች የክህሎትና እውቀት ክፍተቶች ዙሪያ ከተለያዩ የዘርፉ ባለድርሻ አካላት ቀደም ብለው ይነሱ በነበሩት ጥያቄዎች መሠረት እንደሆነ የሳይንስና ከፍተኛ ትምህርት ሚኒስቴር ሚኒስትር ፕሮፌሰር ሂሩት ወልደማርያም ገልጸዋል፡፡\r\n\r\nፕሮፌሰር ሂሩት አክለውም በክለሳው የተማሪዎችን ትምህርት የመቀበል ብቃት፣ አገራዊ ፍላጎት ማሟላት፣ የቋንቋ ብቃት/ክህሎት ማሳደግ (አካዳሚክ እና ኮሙኒኬቲቭ እንግሊዝኛ )፣ ለህይወት ጠቃሚ የሆነውን የኢንተርፕሪነርሺፕ ትምህርት፣ ኤቲክስ መገንባትን ያካተተ መሆኑን ገልጸዋል፡፡ በውይይት መድረኩ የዩኒቨርሲቲ ፕሬዚዳንቶች፣ የቦርድ አባላት፣ የተለያዩ ባለድርሻ ካላት እየተወያዩ ይገኛሉ፡፡', 1, 'የከፍተኛ ትምህርት ስርዓተ - ትምህርት /Curriculum/ ክለሳ አስመልክቶ ከዘርፉ ምሁራን በተዋቀረ ኮሚቴ በተጠናው ረቂቅ ሰነድ ላይ በአዲስ አበባ ከተማ ውይይት እየተካሄደ ይገኛል', NULL, NULL, b'0', NULL, NULL),
(11, 'Admin', NULL, '2019-05-06', 'የሥራ አፈጻጸም ግምገማ ውጤት ጋዜጣዊ መግለጫ፤ የሳይንስና ከፍተኛ ትምህርት ሚኒስቴር በአዲስ መልክ በአዋጅ ቁጥር 1097/2011 ከተቋቋመበት ጊዜ ጀምሮ በሥራ ዕቅድ ያከናወናቸውን የትግበራ ውጤት ግምገማ አስመልክቶ ጋዜጣዊ መግለጫ መስጠቱ ይታወቃል፡፡ በክብርት ሚኒስትር ፕሮፌሰር ሂሩት ወልደማርያም በተሰጠው ጋዜጣዊ መግለጫ ከተለያዩ ተጠሪ ተቋማት እና ከባለድርሻ አካላት ጋር ተገምግመው በጥንካሬና በክፍተት የተለዩ ውጤቶች እንዲሁም የወደፊት የትኩረት አቅጣጫተካተዋል፡፡ የክብርት ሚኒስትሯን ሙሉ መግለጫ ከዚህ ሊንክ ያግኙ ', 1, 'የሳይንስና ከፍተኛ ትምህርት ሚኒስቴር ከተቋቋመበት ጊዜ ጀምሮ በሥራ ዕቅድ ያከናወነው የትግበራ ውጤት', NULL, 23, b'0', NULL, NULL),
(12, 'Admin', NULL, '2019-05-06', ' ', 0, '2011 E.C NIMEI Interview Result and Final Pass/Fail Status', NULL, 25, b'0', NULL, NULL),
(13, 'Admin', NULL, '2019-05-06', 'H.E. Hirut W/Mariyam (Prof.), Minister, Ministry of Science and Higher Education of Ethiopia had signed MoU for lifting cooperation with the Ministry of Science and Higher Education of the Russian Federation in Science and Higher Education sector.\r\n\r\nThe Ministry of Science and Higher Education of the Russian Federation is a ministry established in May 2018 splitting from the Ministry of Education and Science.\r\n\r\nHeadquartered in Moscow, the Ministry is responsible for scientific institutions and the university-level education in the Russian Federation. Consequently, all institutes of the Russian Academy of Sciences are under the jurisdiction of this Ministry.\r\n\r\nIn the underway official work visit Professor Hirut had signed MoU with this Ministry \r\nin advancing cooperation in the field of Science and Higher Education.\r\n\r\nIn addition, after a great meeting on the future cooperation she had signed other MoU with the Director for African Studies in Russian Science Academy.', 0, 'Professor Hirut had signed MoU for cooperation with Russian Minister of Science and Higher Education', NULL, 23, b'0', NULL, NULL),
(14, 'Admin', NULL, '2019-05-06', ' ', 0, '2011 E.C NIMEI Placement', NULL, 24, b'0', NULL, NULL),
(15, 'Admin', NULL, '2019-06-06', 'US President Donald Trump said the US and UK have the \"greatest alliance the world has ever known\" in a news conference with Theresa May.', 0, 'US President Donald Trump said the US and UK', NULL, NULL, b'1', NULL, 'https://www.youtube.com/embed/xnKOtSDJp2E');

-- --------------------------------------------------------

--
-- Table structure for table `news_image`
--

CREATE TABLE `news_image` (
  `id` bigint(20) NOT NULL,
  `alt` varchar(255) DEFAULT NULL,
  `is_main` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `news_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `news_image`
--

INSERT INTO `news_image` (`id`, `alt`, `is_main`, `name`, `news_id`) VALUES
(1, NULL, b'1', '1554782114717.jpg', NULL),
(2, NULL, b'1', '1554797981082.jpg', NULL),
(3, NULL, b'1', '1554799252930.jpg', NULL),
(4, NULL, b'0', '1554799253134.jpg', NULL),
(5, NULL, b'0', '1554799253214.jpg', NULL),
(6, NULL, b'1', '1554901076672.jpg', NULL),
(7, NULL, b'1', '1554901147503.jpg', NULL),
(8, NULL, b'0', '1554901147559.jpg', NULL),
(9, NULL, b'1', '1554989418379.jpg', NULL),
(10, NULL, b'1', '1554989484281.jpg', NULL),
(11, NULL, b'0', '1554989497983.jpg', NULL),
(12, NULL, b'0', '1554989511041.jpg', NULL),
(13, NULL, b'0', '1554989601182.jpg', NULL),
(14, NULL, b'0', '1554989822752.jpg', NULL),
(15, NULL, b'0', '1554989837109.jpg', NULL),
(16, NULL, b'1', '1554990235889.jpg', NULL),
(17, NULL, b'0', '1554990235989.jpg', NULL),
(18, NULL, b'1', '1555669888223.png', NULL),
(19, NULL, b'1', '1557135951221.jpg', NULL),
(20, NULL, b'1', '1557135997048.jpg', NULL),
(21, NULL, b'1', '1557136091902.jpg', NULL),
(22, NULL, b'1', '1557136643270.jpg', NULL),
(23, NULL, b'1', '1557137196569.png', NULL),
(24, NULL, b'1', '1557137246044.png', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `news_images`
--

CREATE TABLE `news_images` (
  `news_id` bigint(20) NOT NULL,
  `images_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `news_images`
--

INSERT INTO `news_images` (`news_id`, `images_id`) VALUES
(8, 19),
(9, 20),
(10, 21),
(12, 24),
(13, 22),
(14, 23);

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE `person` (
  `id` bigint(20) NOT NULL,
  `date_time` date DEFAULT NULL,
  `description` text,
  `language` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `job_id` bigint(20) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`id`, `date_time`, `description`, `language`, `name`, `job_id`, `image`) VALUES
(8, '2019-05-06', '<h2 class=\"ql-align-center\"><br></h2><p class=\"ql-align-justify\"><br></p><p class=\"ql-align-justify\">H.E Hirut Woldemariam (Prof.) is the Minister of Science and Higher Education. She was born on June 05, 1969 in Debremarkos, Gojjam, Ethiopia.</p><p class=\"ql-align-justify\"><br></p><p class=\"ql-align-justify\">H.E Professor Hirut received both her B.A and M.A degrees in Linguistics from Addis Ababa University and her Ph.D. in Linguistics from the Institute of African Studies, University of Cologne in Germany.</p><p class=\"ql-align-justify\"><br></p><p class=\"ql-align-justify\">Her Excellency is a full professor in the Department of Linguistics and Philology, College of Humanities, Journalism and Communication of the Addis Ababa University. She has published a book and more than 30 research articles in different international journals. Before joining the ministerial position, she was serving Addis Ababa University, the oldest and biggest Higher Education Institution in the country, in different leadership positions ranging from Department Head to Vice President.</p><p class=\"ql-align-justify\"><br></p><p class=\"ql-align-justify\">H.E Professor Hirut has served her country as a Minister of Culture and Tourism from 2008 to 2016 and Minister of Labor and Social Affairs from April to October 2018. Since October 2018, she is serving as the founding minister of the newly organized Ministry of Science and Higher Education.</p><p class=\"ql-align-justify\">Prof. Hirut is married to Dr. Tegene Hawando, a surgeon, and has three children.</p><p><br></p>', 0, 'H.E. Hirut Woldemariam (Prof.)', 1, '1557139655437.jpg'),
(9, '2019-05-06', '<p class=\"ql-align-justify\">H.E Samuel Kifle Kidane (PhD) is the State Minister for Science and Higher Education. He was born on 24 December 1978 in a small town of Ilala (Jiaji) in West Shoa, Oromia.&nbsp;Raised in a family making life in mixed farming had the chance to meet People with diverse background&nbsp;and interest in addition to been born from parents of diverse ethnic background.</p><p class=\"ql-align-justify\"><br></p><p class=\"ql-align-justify\">H.E Samuel is appointed By H.E. Prime Minister Abiy Ahmed&nbsp;as State Minister for Science and Higher Education in October 2018, and has earlier served at the Ministry of Education Since October 2013 in different capacities. Between October 2013 and July 2015 He was Director General For Higher Education Administration Affairs; July 2015 to October 2016, he was General Manager for Higher Education Expansion Program; between November 2016 and October 2018&nbsp;he served as State Minister for Higher Education at&nbsp;the Ministry of Education.</p><p class=\"ql-align-justify\"><br></p><p class=\"ql-align-justify\">Started his Career as a Banker where he served for one year as Junior Accountant at Ethiopian Development Bank, joined Jima University in October 2002. For nearly ten years, at Jima University,&nbsp;he taught both Undergraduate and Graduate Students&nbsp;several Accounting &amp; Finance courses advised many students. In addition he has served as Head of Department, Academic Vice dean, Resource Mobilization Director and delegate V. President for Administration Affairs.&nbsp;State Minister Samuel Kifle earned his B.A and M.SC. in Accounting and Finance from Addis Ababa University and PhD from Andhra University with Medal Award (II;2010,2011) for Best Ph.D. Thesis from Faculty of Arts.&nbsp;</p><p class=\"ql-align-justify\"><br></p><p class=\"ql-align-justify\">He is married to Meron Getachew, a father of Nebiy and grew as a little brother of four caring brothers and Sisters( Almaz, Mesfin, Misrak and Milion).</p><p><br></p>', 0, 'H.E. Samuel Kifle (PhD)', 3, '1557139808964.jpg'),
(10, '2019-05-06', '<p class=\"ql-align-justify\">H.E Afework Kassu Gizaw (Prof.) is State Minister of Science and Higher Education (MoSHE) of the Federal Democratic Republic of Ethiopia since October 2018. He is leading the science, and higher education academic and research issues of the MoSHE.</p><p class=\"ql-align-justify\">Before joining MoSHE he served as State Minister of Foreign Affairs (May 2018 – Oct 2018) where he led political diplomacy activities of Continental Affairs 2 (the Americas, Europe, Asia and Oceania, and International Organizations Affairs and International Legal Affairs). He also was State Minister of Science and Technology (Oct 2015 – May 2018) where he led Quality and Regulatory Affairs of the Ministry.&nbsp;</p><p class=\"ql-align-justify\">He also served as Director General for Higher Education Research and Academic Affairs at Federal Ministry of Education from January to October 2015 where he played critical leadership role in higher education research and academic affairs. He served University of Gondar in different capacities from May 1994 to September 2014, including as vice president for Research and Community Services. He has been transferred to Department of Microbiology, Immunology and Parasitology, School of Medicine, College of Health Sciences of Addis Ababa University since October 2014 and serving as Professor of Medical Microbiology and Immunology.&nbsp;</p><p class=\"ql-align-justify\"><br></p><p class=\"ql-align-justify\">Prof. Afework was trained as a biologist and an applied microbiologist at Addis Ababa University in Ethiopia and earned BSc and MSc degrees in 1993 and 2001, respectively. He holds PhD in Biomedical Sciences from The University of Tokushima, Japan in 2007. He also conducted postdoctoral studies at University of Colorado Denver, USA from 2008 to 2010. His broader research involvements include the epidemiology, immunology and molecular biology of infectious diseases with emphasis on HIV/AIDS, tuberculosis, intestinal parasitoses, sexually transmitted infections and other bacterial infections, etc, as demonstrative in over 140 peer reviewed scientific publications in peer reviewed journals in Ethiopia and elsewhere in the world. Prof. Afework has been serving as anonymous reviewer for large number of national and international peer reviewed journals publishing articles in the areas of infectious diseases, microbiology, immunology, nutrition, public health, health services, etc. Prof. Afework is a member of several professional bodies including Ethiopian Public Health Laboratory Association, Ethiopian Society of Tropical and Infectious Diseases, Ethiopian Medical Association, Biological society of Ethiopia, Ethiopian Space Science Society, American Society of Microbiology, and Japanese Society for Bacteriology. He is a Life Member of Ethiopian Public Health Association, Ethiopian Red Cross Society, and Family Guidance Association of Ethiopia.</p><p><br></p>', 0, 'H.E. Afework Kassu (Prof.)', 3, '1557139881215.jpg'),
(11, '2019-05-06', '<p><span style=\"color: rgb(128, 128, 128);\">ክብርት ሂሩት ወልደማርያም (ፕሮፌሰር)&nbsp;የሳይንስና የከፍተኛ ትምህርት ሚኒስትር ናቸው።</span></p>', 1, 'ክብርት ፕሮፌሰር ሂሩት ወልደማርያም', 4, '1557139982543.jpg'),
(12, '2019-05-06', '<p><span style=\"color: rgb(128, 128, 128);\">ክቡር ሳሙኤል ክፍሌ ኪዳኔ (ዶ/ር) የሳይንስና የከፍተኛ ትምህርት ሚኒስትር&nbsp;ዴኤታ ናቸው።&nbsp;</span></p>', 1, 'ክቡር ሳሙኤል ክፍሌ (ዶ/ር)', 5, '1557140027517.jpg'),
(13, '2019-05-06', '<p><span style=\"color: rgb(128, 128, 128);\">ክቡር አፈወርቅ ካሳ ግዛው (ፕሮፌሰር) የሳይንስና የከፍተኛ ትምህርት ሚኒስትር&nbsp;ዴኤታ ናቸው።&nbsp;</span></p>', 1, 'ክቡር ፕሮፌሰር አፈወርቅ ካሳ', 5, '1557140068866.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ADMIN');

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

CREATE TABLE `service` (
  `id` bigint(20) NOT NULL,
  `date_time` date DEFAULT NULL,
  `description` text,
  `language` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `service`
--

INSERT INTO `service` (`id`, `date_time`, `description`, `language`, `title`, `link`) VALUES
(2, '2019-04-08', 'This portal is used to collect inquiries needed by the Ministry of Science and Higher Education. The system can assist results of the inquiries can be used in policy making process, to check the effectiveness of implemented plans and Collect data in a timely and effective manner.', 0, 'Data Collection and Processing Portal', 'http://facebook.com'),
(3, '2019-04-08', 'Job Portal is web application for governmental organizations to post Vacancies, collect CV and other pertaining documents from applicants. It is also a platform that let users to register, view get notified of new vacancy posts.', 0, 'Public Job portal', 'http://facebook.com'),
(4, '2019-04-08', 'This system targets on solving the time-consuming process of allocating applicants on government sponsored education for masters and PHD from government universities. It easily and effectively allocate applicants to available universities and fields based on standard procedures.', 0, 'Applicant Allocation System', 'http://facebook.com'),
(5, '2019-04-08', 'የተለያዩ መንግስታዊ ተቋማት CV እና ሌሎች ተዛማጅ ሰነዶችን ከስራ አመልካቾችን ለመመዝገብ የሚያስችል ስርዓት ነው:: ተጠቃሚዎች እንዲመዘገቡ የሚያስችልና አዲስ የአዳዲስ ክፍት የስራ ቦታዎች ማስታወቂያ እንዲደርሳቸው ይረዳል::', 1, 'Public Job portal', ''),
(6, '2019-04-08', 'ይህ የመረጃ መሰብሰቢያ በሳይንስና ከፍተኛ ትምህርት ሚኒስቴር የሚፈለጉ ጥያቄዎችን ለመሰብሰብ ያገለግላል፡፡ ስርዓቱ የፖሊሲ አሰጣጥ ሂደት ለማገዝ, የተተገበሩ ፕላኖችን ውጤታማነት ለመፈተሽ እና መረጃን በጊዜ እና ውጤታማ በሆነ መንገድ ለማሰባሰብ ይረዳል፡፡', 1, 'አምጡ', ''),
(7, '2019-04-09', 'ይህ ስርዓት በመንግስት ዩኒቨርሲቲዎች ውስጥ በመንግስት ስፖንሰርሺፕ ውስጥ ለሚሰሩ ትምህርት ቤቶች አመልካቾችን ለመመደብ ጊዜ የሚፈጅበትን ሂደት ለማረም ያተኮረ ነው. በዩኒቨርሲቲዎች እና በመሰረታዊ ደረጃዎች ላይ በመመርኮዝ አመልካቾችን በቀላሉ እና በተገቢው ሁኔታ ለመመደብ', 1, 'Applicant Allocation System', 'http://facebook.com'),
(9, '2019-04-12', 'MoSHE Service provides the list of services given by the Ministry of Science and Higher Education. Users will be able to browse services lists, steps, requirements and responsible offices for each in order to get the required service.', 0, 'MoSHE Services', '');

-- --------------------------------------------------------

--
-- Table structure for table `subscriber`
--

CREATE TABLE `subscriber` (
  `id` bigint(20) NOT NULL,
  `date_time` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `suggestion`
--

CREATE TABLE `suggestion` (
  `id` bigint(20) NOT NULL,
  `content` text,
  `date_time` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tender`
--

CREATE TABLE `tender` (
  `id` bigint(20) NOT NULL,
  `content` text,
  `date_time` date DEFAULT NULL,
  `deadline` date DEFAULT NULL,
  `description` text,
  `image` varchar(255) DEFAULT NULL,
  `language` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `full_name`, `password`, `username`) VALUES
(5, 'Admin Name', '$2a$10$lNlTECS6NZGJVcqcE9gg.eTZtK2fRLArEYCWQ8Jq0MxjaCn30ZT4a', 'moshe');

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `roles_id`) VALUES
(5, 1);

-- --------------------------------------------------------

--
-- Table structure for table `vacancy`
--

CREATE TABLE `vacancy` (
  `id` bigint(20) NOT NULL,
  `content` text,
  `date_time` date DEFAULT NULL,
  `deadline` date DEFAULT NULL,
  `description` text,
  `image` varchar(255) DEFAULT NULL,
  `language` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `document`
--
ALTER TABLE `document`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKe2no7rafy2kby8devwkuyfak3` (`document_type_id`);

--
-- Indexes for table `document_type`
--
ALTER TABLE `document_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `general_inforamtion`
--
ALTER TABLE `general_inforamtion`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `job`
--
ALTER TABLE `job`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `link`
--
ALTER TABLE `link`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `news`
--
ALTER TABLE `news`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1g60y0t654ay3w3atxl41qmo6` (`job_id`),
  ADD KEY `FKgstsvg6v0nb1a0xij92qemx85` (`document_id`);

--
-- Indexes for table `news_image`
--
ALTER TABLE `news_image`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhwajir19rbhvdif5as4g6wcwf` (`news_id`);

--
-- Indexes for table `news_images`
--
ALTER TABLE `news_images`
  ADD UNIQUE KEY `UK_8caof24wulbu7hhfuwlm1kq9u` (`images_id`),
  ADD KEY `FKhcwu8kdkcw4mkqlygdq0g3dtq` (`news_id`);

--
-- Indexes for table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK46w972hjfkqrhuvn4y47i80mp` (`job_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subscriber`
--
ALTER TABLE `subscriber`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `suggestion`
--
ALTER TABLE `suggestion`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tender`
--
ALTER TABLE `tender`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`user_id`,`roles_id`),
  ADD KEY `FKj9553ass9uctjrmh0gkqsmv0d` (`roles_id`);

--
-- Indexes for table `vacancy`
--
ALTER TABLE `vacancy`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `document`
--
ALTER TABLE `document`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `document_type`
--
ALTER TABLE `document_type`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `general_inforamtion`
--
ALTER TABLE `general_inforamtion`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `job`
--
ALTER TABLE `job`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `link`
--
ALTER TABLE `link`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `news`
--
ALTER TABLE `news`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `news_image`
--
ALTER TABLE `news_image`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `person`
--
ALTER TABLE `person`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `service`
--
ALTER TABLE `service`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `subscriber`
--
ALTER TABLE `subscriber`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `suggestion`
--
ALTER TABLE `suggestion`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tender`
--
ALTER TABLE `tender`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `vacancy`
--
ALTER TABLE `vacancy`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `document`
--
ALTER TABLE `document`
  ADD CONSTRAINT `FKe2no7rafy2kby8devwkuyfak3` FOREIGN KEY (`document_type_id`) REFERENCES `document_type` (`id`);

--
-- Constraints for table `news`
--
ALTER TABLE `news`
  ADD CONSTRAINT `FK1g60y0t654ay3w3atxl41qmo6` FOREIGN KEY (`job_id`) REFERENCES `document` (`id`),
  ADD CONSTRAINT `FKgstsvg6v0nb1a0xij92qemx85` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`);

--
-- Constraints for table `news_image`
--
ALTER TABLE `news_image`
  ADD CONSTRAINT `FKhwajir19rbhvdif5as4g6wcwf` FOREIGN KEY (`news_id`) REFERENCES `news` (`id`);

--
-- Constraints for table `news_images`
--
ALTER TABLE `news_images`
  ADD CONSTRAINT `FKhcwu8kdkcw4mkqlygdq0g3dtq` FOREIGN KEY (`news_id`) REFERENCES `news` (`id`),
  ADD CONSTRAINT `FKs5qjykibp5q7s5vsr7cw8nfc9` FOREIGN KEY (`images_id`) REFERENCES `news_image` (`id`);

--
-- Constraints for table `person`
--
ALTER TABLE `person`
  ADD CONSTRAINT `FK46w972hjfkqrhuvn4y47i80mp` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`);

--
-- Constraints for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `FK55itppkw3i07do3h7qoclqd4k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKj9553ass9uctjrmh0gkqsmv0d` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
