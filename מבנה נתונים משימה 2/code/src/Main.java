import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;



public class Main {

	public static void main(String[] args) 
	{
		//testA();
		//testB();
		//testC();
	}


    private static double distance(Point p1, Point p2)
    {
        return Math.sqrt( Math.pow(p1.getX()-p2.getX(),2)    +Math.pow(p1.getY()-p2.getY(),2)    );
    }


	private static void testA()
	{
		PointCompareX comparatorX = new PointCompareX();
		PointCompareY comparatorY = new PointCompareY();
		int testCounter=0;
		Point[] points = {
				new Point(0, 0), 
				new Point(1, 1),
				new Point(4, 2),
				new Point(3, 3),
				new Point(2, 4),
				};
		
		
		
		DT dt=new DataStructure();
		for (int i=0;i<points.length;i++)
			dt.addPoint(points[i]);
		String testName;
		int expected;
		int result;
		//0
		testName = "A"+testCounter;testCounter++;
		testExpectedPoints(testName, points, dt.getPointsInRangeRegAxis(-1, 6, false));
		//1
		testName = "A"+testCounter;testCounter++;
		testExpectedPoints(testName, points, dt.getPointsInRangeOppAxis(-1, 6, true));
		//2
		Arrays.sort(points,comparatorX);
		testName = "A"+testCounter;testCounter++;
		testExpectedPoints(testName, points, dt.getPointsInRangeRegAxis(-1, 6, true));
		//3
		testName = "A"+testCounter;testCounter++;
		testExpectedPoints(testName, points, dt.getPointsInRangeOppAxis(-1, 6, false));
        //4
		testName = "A"+testCounter;testCounter++;		
		Point[] expectedPoints = 
			{
				new Point(3, 3),
				new Point(2, 4),
			};
		testExpectedPoints(testName, expectedPoints, dt.getPointsInRangeRegAxis(3, 7, false));
		
		Arrays.sort(expectedPoints,comparatorX);
		testName = "A"+testCounter;testCounter++;
		testExpectedPoints(testName, expectedPoints, dt.getPointsInRangeOppAxis(3, 7, false));
		
		testName = "A"+testCounter;testCounter++;		
		Point[] expectedPoints2 = 
			{
				new Point(4, 2),
				new Point(3, 3),
				
			};
		testExpectedPoints(testName, expectedPoints2, dt.getPointsInRangeOppAxis(3, 7, true));
		
		Arrays.sort(expectedPoints2,comparatorX);
		testName = "A"+testCounter;testCounter++;
		testExpectedPoints(testName, expectedPoints2, dt.getPointsInRangeRegAxis(3, 7, true));
	}

	

    private static Point[] generatePointsForTestCaseB()
    {
        Point[] points = {

                new Point(186, 172),
                new Point(207, 180),
                new Point(571, 907),
                new Point(905, 567),
                new Point(313, 105),
                new Point(963, 821),
                new Point(609, 410),
                new Point(411, 686),
                new Point(164, 1),
                new Point(980, 340),
                new Point(515, 286),
                new Point(548, 23),
                new Point(568, 707),
                new Point(320, 406),
                new Point(220, 726),
                new Point(249, 740),
                new Point(101, 769),
                new Point(76, 77),
                new Point(883, 909),
                new Point(332, 588),
                new Point(781, 626),
                new Point(395, 717),
                new Point(856, 914),
                new Point(851, 44),
                new Point(412, 198),
                new Point(728, 210),
                new Point(109, 364),
                new Point(969, 68),
                new Point(986, 402),
                new Point(798, 432),
                new Point(792, 83),
                new Point(176, 373),
                new Point(507, 847),
                new Point(77, 379),
                new Point(592, 475),
                new Point(387, 160),
                new Point(628, 649),
                new Point(632, 652),
                new Point(366, 723),
                new Point(683, 785),
                new Point(673, 725),
                new Point(111, 713),
                new Point(33, 150),
                new Point(556, 70),
                new Point(419, 230),
                new Point(731, 790),
                new Point(514, 826),
                new Point(51, 271),
                new Point(738, 338),
                new Point(655, 261),
                new Point(138, 651),
                new Point(524, 986),
                new Point(443, 702),
                new Point(844, 211),
                new Point(796, 564),
                new Point(422, 807),
                new Point(463, 995),
                new Point(482, 205),
                new Point(440, 529),
                new Point(589, 18),
                new Point(285, 582),
                new Point(418, 106),
                new Point(116, 549),
                new Point(852, 52),
                new Point(222, 714),
                new Point(365, 599),
                new Point(933, 232),
                new Point(601, 638),
                new Point(566, 525),
                new Point(34, 500),
                new Point(241, 27),
                new Point(805, 259),
                new Point(698, 275),
                new Point(4, 203),
                new Point(218, 755),
                new Point(72, 258),
                new Point(300, 128),
                new Point(245, 120),
                new Point(775, 794),
                new Point(756, 470),
                new Point(795, 469),
                new Point(829, 937),
                new Point(598, 964),
                new Point(110, 988),
                new Point(185, 439),
                new Point(638, 793),
                new Point(885, 400),
                new Point(918, 465),
                new Point(430, 356),
                new Point(839, 774),
                new Point(536, 225),
                new Point(65, 990),
                new Point(522, 162),
                new Point(665, 596),
                new Point(162, 783),
                new Point(357, 484),
                new Point(229, 997),
                new Point(636, 830),
                new Point(719, 828),
                new Point(492, 314),
                new Point(630, 949),
                new Point(486, 751),
                new Point(302, 395),
                new Point(540, 32),
                new Point(312, 191),
                new Point(691, 928),
                new Point(593, 265),
                new Point(504, 513),
                new Point(835, 727),
                new Point(237, 71),
                new Point(203, 267),
                new Point(384, 823),
                new Point(585, 613),
                new Point(356, 696),
                new Point(221, 177),
                new Point(0, 750),
                new Point(400, 520),
                new Point(15, 571),
                new Point(12, 813),
                new Point(892, 680),
                new Point(99, 328),
                new Point(825, 674),
                new Point(722, 896),
                new Point(147, 934),
                new Point(289, 394),
                new Point(631, 508),
                new Point(901, 533),
                new Point(520, 801),
                new Point(888, 237),
                new Point(794, 947),
                new Point(783, 56),
                new Point(432, 372),
                new Point(947, 816),
                new Point(18, 155),
                new Point(640, 968),
                new Point(541, 889),
                new Point(329, 698),
                new Point(24, 108),
                new Point(152, 252),
                new Point(706, 122),
                new Point(684, 244),
                new Point(761, 631),
                new Point(557, 472),
                new Point(861, 352),
                new Point(702, 424),
                new Point(803, 913),
                new Point(381, 673),
                new Point(745, 645),
                new Point(98, 7),
                new Point(587, 69),
                new Point(570, 140),
                new Point(779, 54),
                new Point(613, 6),
                new Point(263, 107),
                new Point(485, 566),
                new Point(370, 280),
                new Point(498, 675),
                new Point(855, 214),
                new Point(448, 639),
                new Point(88, 747),
                new Point(293, 31),
                new Point(30, 697),
                new Point(923, 952),
                new Point(180, 738),
                new Point(158, 362),
                new Point(380, 324),
                new Point(43, 708),
                new Point(11, 979),
                new Point(337, 346),
                new Point(259, 46),
                new Point(71, 322),
                new Point(992, 380),
                new Point(928, 700),
                new Point(734, 401),
                new Point(141, 349),
                new Point(990, 999),
                new Point(545, 574),
                new Point(286, 693),
                new Point(338, 915),
                new Point(466, 460),
                new Point(311, 90),
                new Point(979, 983),
                new Point(748, 940),
                new Point(198, 978),
                new Point(386, 749),
                new Point(732, 817),
                new Point(168, 661),
                new Point(908, 977),
                new Point(621, 276),
                new Point(48, 614),
                new Point(122, 834),
                new Point(724, 255),
                new Point(331, 627),
                new Point(882, 558),
                new Point(127, 93),
                new Point(875, 559),
                new Point(811, 57),
                new Point(567, 578),
                new Point(806, 989),
                new Point(85, 695),
                new Point(56, 51),
                new Point(735, 971),
                new Point(309, 659),
                new Point(246, 300),
                new Point(651, 973),
                new Point(385, 688),
                new Point(546, 363),
                new Point(983, 875),
                new Point(846, 461),
                new Point(376, 511),
                new Point(417, 478),
                new Point(693, 233),
                new Point(202, 88),
                new Point(91, 831),
                new Point(718, 841),
                new Point(521, 703),
                new Point(472, 103),
                new Point(547, 560),
                new Point(907, 864),
                new Point(446, 136),
                new Point(261, 58),
                new Point(227, 169),
                new Point(32, 758),
                new Point(208, 0),
                new Point(425, 597),
                new Point(35, 82),
                new Point(820, 512),
                new Point(550, 269),
                new Point(552, 683),
                new Point(40, 393),
                new Point(345, 634),
                new Point(777, 795),
                new Point(133, 628),
                new Point(403, 903),
                new Point(233, 433),
                new Point(473, 181),
                new Point(604, 179),
                new Point(898, 222),
                new Point(678, 715),
                new Point(256, 547),
                new Point(27, 384),
                new Point(341, 666),
                new Point(991, 604),
                new Point(271, 630),
                new Point(778, 246),
                new Point(84, 386),
                new Point(192, 334),
                new Point(516, 312),
                new Point(169, 610),
                new Point(393, 193),
                new Point(626, 145),
                new Point(793, 310),
                new Point(398, 859),
                new Point(710, 33),
                new Point(595, 908),
                new Point(130, 541),
                new Point(449, 78),
                new Point(696, 382),
                new Point(670, 678),
                new Point(2, 183),
                new Point(808, 905),
                new Point(188, 194),
                new Point(317, 842),
                new Point(160, 399),
                new Point(1, 791),
                new Point(232, 926),
                new Point(217, 307),
                new Point(997, 846),
                new Point(420, 741),
                new Point(654, 705),
                new Point(993, 669),
                new Point(929, 553),
                new Point(945, 178),
                new Point(964, 305),
                new Point(725, 737),
                new Point(876, 266),
                new Point(433, 532),
                new Point(682, 570),
                new Point(954, 611),
                new Point(840, 289),
                new Point(512, 852),
                new Point(904, 732),
                new Point(243, 911),
                new Point(700, 967),
                new Point(108, 353),
                new Point(733, 898),
                new Point(863, 895),
                new Point(90, 218),
                new Point(669, 590),
                new Point(324, 212),
                new Point(476, 799),
                new Point(810, 701),
                new Point(80, 309),
                new Point(8, 138),
                new Point(22, 782),
                new Point(909, 50),
                new Point(304, 485),
                new Point(968, 904),
                new Point(9, 381),
                new Point(335, 648),
                new Point(617, 943),
                new Point(716, 974),
                new Point(804, 954),
                new Point(768, 359),
                new Point(915, 692),
                new Point(373, 97),
                new Point(828, 765),
                new Point(723, 872),
                new Point(581, 527),
                new Point(643, 536),
                new Point(272, 745),
                new Point(5, 770),
                new Point(153, 746),
                new Point(590, 304),
                new Point(843, 49),
                new Point(880, 526),
                new Point(471, 984),
                new Point(140, 316),
                new Point(156, 442),
                new Point(131, 220),
                new Point(174, 704),
                new Point(193, 650),
                new Point(146, 886),
                new Point(704, 223),
                new Point(490, 35),
                new Point(137, 972),
                new Point(955, 690),
                new Point(900, 654),
                new Point(561, 522),
                new Point(49, 624),
                new Point(423, 729),
                new Point(558, 454),
                new Point(797, 149),
                new Point(166, 523),
                new Point(69, 436),
                new Point(606, 14),
                new Point(495, 948),
                new Point(699, 699),
                new Point(354, 72),
                new Point(195, 656),
                new Point(125, 754),
                new Point(894, 462),
                new Point(264, 537),
                new Point(406, 724),
                new Point(878, 323),
                new Point(741, 391),
                new Point(563, 457),
                new Point(572, 857),
                new Point(712, 505),
                new Point(832, 10),
                new Point(149, 392),
                new Point(402, 563),
                new Point(785, 507),
                new Point(334, 542),
                new Point(242, 603),
                new Point(429, 412),
                new Point(859, 272),
                new Point(154, 681),
                new Point(629, 568),
                new Point(976, 561),
                new Point(865, 119),
                new Point(315, 530),
                new Point(668, 158),
                new Point(995, 135),
                new Point(660, 241),
                new Point(325, 296),
                new Point(456, 299),
                new Point(913, 862),
                new Point(914, 860),
                new Point(89, 998),
                new Point(36, 236),
                new Point(134, 213),
                new Point(948, 87),
                new Point(378, 67),
                new Point(314, 459),
                new Point(658, 957),
                new Point(361, 405),
                new Point(646, 921),
                new Point(151, 361),
                new Point(827, 900),
                new Point(475, 888),
                new Point(362, 923),
                new Point(726, 927),
                new Point(278, 612),
                new Point(707, 767),
                new Point(740, 768),
                new Point(615, 676),
                new Point(970, 13),
                new Point(525, 894),
                new Point(648, 277),
                new Point(465, 849),
                new Point(73, 965),
                new Point(47, 625),
                new Point(960, 318),
                new Point(282, 421),
                new Point(206, 812),
                new Point(560, 175),
                new Point(74, 836),
                new Point(596, 219),
                new Point(711, 950),
                new Point(927, 365),
                new Point(789, 376),
                new Point(382, 173),
                new Point(123, 961),
                new Point(327, 430),
                new Point(715, 385),
                new Point(143, 420),
                new Point(444, 655),
                new Point(588, 21),
                new Point(530, 839),
                new Point(848, 311),
                new Point(729, 47),
                new Point(607, 916),
                new Point(877, 653),
                new Point(230, 473),
                new Point(985, 936),
                new Point(290, 34),
                new Point(921, 528),
                new Point(527, 74),
                new Point(487, 331),
                new Point(925, 800),
                new Point(467, 254),
                new Point(903, 924),
                new Point(841, 129),
                new Point(303, 132),
                new Point(802, 425),
                new Point(618, 431),
                new Point(479, 224),
                new Point(780, 953),
                new Point(675, 320),
                new Point(436, 544),
                new Point(573, 586),
                new Point(807, 25),
                new Point(31, 302),
                new Point(94, 66),
                new Point(755, 985),
                new Point(310, 59),
                new Point(322, 575),
                new Point(608, 691),
                new Point(53, 231),
                new Point(351, 719),
                new Point(28, 333),
                new Point(44, 671),
                new Point(920, 415),
                new Point(328, 585),
                new Point(518, 468),
                new Point(81, 130),
                new Point(635, 877),
                new Point(544, 112),
                new Point(845, 327),
                new Point(58, 730),
                new Point(644, 814),
                new Point(92, 837),
                new Point(57, 378),
                new Point(305, 126),
                new Point(287, 117),
                new Point(790, 868),
                new Point(513, 757),
                new Point(786, 153),
                new Point(578, 295),
                new Point(119, 235),
                new Point(681, 893),
                new Point(966, 215),
                new Point(506, 494),
                new Point(989, 270),
                new Point(280, 298),
                new Point(388, 28),
                new Point(254, 808),
                new Point(171, 710),
                new Point(528, 629),
                new Point(599, 663),
                new Point(224, 351),
                new Point(838, 95),
                new Point(591, 38),
                new Point(59, 184),
                new Point(339, 249),
                new Point(96, 637),
                new Point(457, 557),
                new Point(936, 581),
                new Point(120, 495),
                new Point(850, 601),
                new Point(627, 374),
                new Point(292, 622),
                new Point(813, 238),
                new Point(347, 441),
                new Point(349, 170),
                new Point(431, 55),
                new Point(517, 573),
                new Point(14, 396),
                new Point(881, 197),
                new Point(703, 321),
                new Point(680, 482),
                new Point(68, 722),
                new Point(930, 944),
                new Point(559, 17),
                new Point(353, 615),
                new Point(611, 163),
                new Point(389, 798),
                new Point(849, 763),
                new Point(144, 42),
                new Point(652, 5),
                new Point(837, 319),
                new Point(6, 3),
                new Point(343, 572),
                new Point(701, 166),
                new Point(695, 515),
                new Point(818, 994),
                new Point(823, 811),
                new Point(255, 20),
                new Point(869, 101),
                new Point(97, 519),
                new Point(408, 240),
                new Point(819, 189),
                new Point(257, 501),
                new Point(474, 111),
                new Point(509, 551),
                new Point(653, 139),
                new Point(178, 268),
                new Point(826, 416),
                new Point(549, 496),
                new Point(709, 861),
                new Point(60, 156),
                new Point(953, 329),
                new Point(965, 40),
                new Point(744, 493),
                new Point(201, 345),
                new Point(511, 731),
                new Point(951, 398),
                new Point(639, 239),
                new Point(358, 8),
                new Point(86, 591),
                new Point(342, 938),
                new Point(267, 835),
                new Point(565, 164),
                new Point(957, 657),
                new Point(350, 293),
                new Point(170, 606),
                new Point(759, 976),
                new Point(438, 718),
                new Point(139, 325),
                new Point(714, 580),
                new Point(600, 543),
                new Point(645, 660),
                new Point(564, 509),
                new Point(295, 96),
                new Point(247, 22),
                new Point(181, 667),
                new Point(175, 24),
                new Point(150, 208),
                new Point(974, 371),
                new Point(625, 759),
                new Point(757, 26),
                new Point(394, 80),
                new Point(288, 486),
                new Point(367, 694),
                new Point(773, 474),
                new Point(634, 171),
                new Point(126, 775),
                new Point(262, 330),
                new Point(860, 76),
                new Point(619, 142),
                new Point(265, 518),
                new Point(554, 802),
                new Point(529, 209),
                new Point(55, 243),
                new Point(972, 341),
                new Point(697, 281),
                new Point(766, 491),
                new Point(29, 228),
                new Point(862, 538),
                new Point(676, 388),
                new Point(586, 127),
                new Point(142, 278),
                new Point(501, 455),
                new Point(836, 743),
                new Point(410, 151),
                new Point(864, 711),
                new Point(63, 621),
                new Point(831, 584),
                new Point(594, 248),
                new Point(359, 4),
                new Point(464, 452),
                new Point(205, 419),
                new Point(252, 514),
                new Point(200, 764),
                new Point(95, 437),
                new Point(159, 838),
                new Point(988, 721),
                new Point(238, 539),
                new Point(720, 190),
                new Point(215, 336),
                new Point(842, 133),
                new Point(727, 283),
                new Point(687, 786),
                new Point(344, 247),
                new Point(961, 2),
                new Point(333, 279),
                new Point(962, 773),
                new Point(967, 609),
                new Point(26, 284),
                new Point(822, 315),
                new Point(998, 506),
                new Point(647, 116),
                new Point(336, 879),
                new Point(190, 919),
                new Point(987, 440),
                new Point(772, 865),
                new Point(577, 29),
                new Point(602, 760),
                new Point(776, 531),
                new Point(23, 521),
                new Point(20, 756),
                new Point(437, 980),
                new Point(184, 355),
                new Point(268, 897),
                new Point(321, 350),
                new Point(291, 182),
                new Point(191, 48),
                new Point(401, 306),
                new Point(543, 143),
                new Point(161, 744),
                new Point(579, 833),
                new Point(959, 672),
                new Point(37, 925),
                new Point(981, 991),
                new Point(926, 619),
                new Point(283, 418),
                new Point(250, 945),
                new Point(886, 545),
                new Point(771, 195),
                new Point(946, 635),
                new Point(562, 762),
                new Point(624, 618),
                new Point(494, 890),
                new Point(574, 946),
                new Point(743, 855),
                new Point(360, 75),
                new Point(308, 595),
                new Point(895, 752),
                new Point(977, 429),
                new Point(637, 728),
                new Point(787, 535),
                new Point(93, 534),
                new Point(371, 577),
                new Point(461, 935),
                new Point(114, 848),
                new Point(404, 19),
                new Point(172, 141),
                new Point(372, 885),
                new Point(950, 497),
                new Point(603, 357),
                new Point(19, 623),
                new Point(553, 317),
                new Point(800, 186),
                new Point(213, 840),
                new Point(226, 856),
                new Point(294, 464),
                new Point(519, 797),
                new Point(460, 592),
                new Point(662, 975),
                new Point(580, 642),
                new Point(791, 843),
                new Point(113, 256),
                new Point(496, 933),
                new Point(641, 776),
                new Point(971, 617),
                new Point(747, 720),
                new Point(105, 881),
                new Point(531, 963),
                new Point(469, 41),
                new Point(939, 664),
                new Point(902, 644),
                new Point(943, 187),
                new Point(897, 63),
                new Point(128, 407),
                new Point(887, 131),
                new Point(62, 347),
                new Point(260, 556),
                new Point(236, 229),
                new Point(223, 358),
                new Point(266, 712),
                new Point(284, 37),
                new Point(346, 250),
                new Point(623, 377),
                new Point(251, 804),
                new Point(753, 641),
                new Point(597, 15),
                new Point(672, 647),
                new Point(824, 887),
                new Point(769, 822),
                new Point(145, 154),
                new Point(760, 892),
                new Point(379, 335),
                new Point(348, 874),
                new Point(46, 308),
                new Point(812, 313),
                new Point(426, 602),
                new Point(884, 423),
                new Point(996, 202),
                new Point(204, 413),
                new Point(620, 368),
                new Point(16, 958),
                new Point(326, 428),
                new Point(52, 576),
                new Point(374, 9),
                new Point(112, 917),
                new Point(510, 144),
                new Point(7, 956),
                new Point(167, 620),
                new Point(499, 810),
                new Point(21, 803),
                new Point(318, 679),
                new Point(688, 882),
                new Point(199, 147),
                new Point(75, 858),
                new Point(788, 53),
                new Point(187, 199),
                new Point(940, 736),
                new Point(258, 404),
                new Point(54, 123),
                new Point(500, 734),
                new Point(148, 665),
                new Point(538, 670),
                new Point(488, 931),
                new Point(489, 498),
                new Point(355, 788),
                new Point(874, 565),
                new Point(809, 608),
                new Point(692, 443),
                new Point(248, 844),
                new Point(686, 297),
                new Point(649, 869),
                new Point(935, 920),
                new Point(978, 360),
                new Point(41, 682),
                new Point(767, 253),
                new Point(235, 354),
                new Point(737, 685),
                new Point(912, 819),
                new Point(533, 370),
                new Point(952, 85),
                new Point(87, 677),
                new Point(934, 343),
                new Point(663, 955),
                new Point(694, 344),
                new Point(270, 36),
                new Point(679, 444),
                new Point(906, 60),
                new Point(736, 593),
                new Point(480, 375),
                new Point(61, 98),
                new Point(352, 369),
                new Point(439, 845),
                new Point(764, 854),
                new Point(377, 555),
                new Point(891, 387),
                new Point(854, 234),
                new Point(784, 30),
                new Point(958, 550),
                new Point(899, 910),
                new Point(889, 134),
                new Point(279, 411),
                new Point(39, 866),
                new Point(910, 918),
                new Point(447, 716),
                new Point(273, 815),
                new Point(941, 805),
                new Point(189, 912),
                new Point(421, 779),
                new Point(458, 766),
                new Point(330, 871),
                new Point(622, 427),
                new Point(435, 951),
                new Point(975, 899),
                new Point(163, 161),
                new Point(340, 73),
                new Point(319, 824),
                new Point(667, 742),
                new Point(730, 492),
                new Point(83, 546),
                new Point(407, 771),
                new Point(867, 148),
                new Point(454, 435),
                new Point(67, 466),
                new Point(664, 192),
                new Point(816, 426),
                new Point(765, 689),
                new Point(774, 196),
                new Point(442, 524),
                new Point(493, 326),
                new Point(705, 292),
                new Point(214, 605),
                new Point(209, 109),
                new Point(763, 502),
                new Point(368, 970),
                new Point(441, 959),
                new Point(721, 761),
                new Point(657, 422),
                new Point(306, 792),
                new Point(240, 939),
                new Point(477, 777),
                new Point(25, 445),
                new Point(916, 487),
                new Point(307, 548),
                new Point(690, 636),
                new Point(834, 301),
                new Point(503, 781),
                new Point(917, 987),
                new Point(183, 587),
                new Point(177, 200),
                new Point(872, 285),
                new Point(103, 832),
                new Point(38, 99),
                new Point(801, 342),
                new Point(118, 479),
                new Point(173, 780),
                new Point(228, 65),
                new Point(136, 863),
                new Point(179, 165),
                new Point(219, 942),
                new Point(922, 389),
                new Point(944, 867),
                new Point(231, 282),
                new Point(100, 739),
                new Point(526, 125),
                new Point(299, 79),
                new Point(10, 499),
                new Point(833, 540),
                new Point(754, 467),
                new Point(451, 598),
                new Point(459, 806),
                new Point(616, 61),
                new Point(610, 902),
                new Point(157, 204),
                new Point(121, 456),
                new Point(455, 242),
                new Point(708, 273),
                new Point(234, 477),
                new Point(942, 941),
                new Point(396, 458),
                new Point(102, 873),
                new Point(582, 274),
                new Point(551, 733),
                new Point(117, 367),
                new Point(890, 290),
                new Point(301, 992),
                new Point(462, 483),
                new Point(931, 146),
                new Point(115, 114),
                new Point(79, 403),
                new Point(873, 86),
                new Point(770, 383),
                new Point(984, 167),
                new Point(277, 348),
                new Point(853, 883),
                new Point(107, 137),
                new Point(197, 263),
                new Point(999, 206),
                new Point(50, 408),
                new Point(129, 409),
                new Point(866, 366),
                new Point(605, 176),
                new Point(453, 643),
                new Point(416, 818),
                new Point(815, 684),
                new Point(124, 579),
                new Point(275, 152),
                new Point(413, 115),
                new Point(949, 850),
                new Point(392, 207),
                new Point(194, 853),
                new Point(532, 594),
                new Point(296, 827),
                new Point(896, 982),
                new Point(847, 569),
                new Point(575, 562),
                new Point(868, 185),
                new Point(274, 753),
                new Point(253, 463),
                new Point(659, 303),
                new Point(225, 922),
                new Point(397, 157),
                new Point(450, 39),
                new Point(982, 438),
                new Point(505, 966),
                new Point(938, 481),
                new Point(470, 332),
                new Point(749, 616),
                new Point(750, 516),
                new Point(363, 503),
                new Point(858, 287),
                new Point(269, 102),
                new Point(583, 930),
                new Point(739, 876),
                new Point(42, 706),
                new Point(924, 397),
                new Point(614, 554),
                new Point(491, 929),
                new Point(481, 217),
                new Point(674, 337),
                new Point(414, 251),
                new Point(539, 981),
                new Point(424, 962),
                new Point(758, 446),
                new Point(298, 92),
                new Point(746, 510),
                new Point(994, 884),
                new Point(661, 809),
                new Point(3, 517),
                new Point(830, 449),
                new Point(870, 829),
                new Point(135, 62),
                new Point(612, 450),
                new Point(64, 607),
                new Point(409, 245),
                new Point(799, 94),
                new Point(814, 932),
                new Point(717, 906),
                new Point(468, 709),
                new Point(576, 480),
                new Point(434, 262),
                new Point(66, 748),
                new Point(497, 789),
                new Point(399, 104),
                new Point(281, 91),
                new Point(165, 969),
                new Point(196, 658),
                new Point(919, 226),
                new Point(484, 993),
                new Point(478, 64),
                new Point(650, 633),
                new Point(633, 174),
                new Point(956, 489),
                new Point(364, 81),
                new Point(239, 960),
                new Point(555, 796),
                new Point(405, 118),
                new Point(390, 640),
                new Point(508, 632),
                new Point(671, 687),
                new Point(857, 124),
                new Point(523, 772),
                new Point(689, 260),
                new Point(534, 735),
                new Point(782, 294),
                new Point(210, 851),
                new Point(911, 45),
                new Point(584, 447),
                new Point(132, 159),
                new Point(666, 451),
                new Point(445, 417),
                new Point(182, 168),
                new Point(483, 646),
                new Point(542, 11),
                new Point(106, 113),
                new Point(535, 390),
                new Point(369, 414),
                new Point(82, 448),
                new Point(391, 188),
                new Point(211, 583),
                new Point(415, 490),
                new Point(502, 16),
                new Point(642, 216),
                new Point(45, 43),
                new Point(871, 84),
                new Point(821, 257),
                new Point(427, 662),
                new Point(452, 820),
                new Point(155, 880),
                new Point(17, 787),
                new Point(656, 471),
                new Point(742, 12),
                new Point(817, 552),
                new Point(216, 488),
                new Point(537, 89),
                new Point(212, 589),
                new Point(428, 825),
                new Point(973, 504),
                new Point(569, 600),
                new Point(932, 291),
                new Point(383, 453),
                new Point(893, 878),
                new Point(78, 784),
                new Point(13, 221),
                new Point(70, 901),
                new Point(685, 121),
                new Point(677, 996),
                new Point(104, 288),
                new Point(879, 668),
                new Point(297, 339),
                new Point(713, 227),
                new Point(276, 870),
                new Point(751, 100),
                new Point(316, 264),
                new Point(937, 201),
                new Point(375, 778),
                new Point(323, 476),
                new Point(752, 891),
                new Point(762, 110),
                new Point(244, 434),

        };
        return points;
    }

    private  static void testB()
    {
        Point[] points = generatePointsForTestCaseB();

        PointCompareX comparatorX = new PointCompareX();
        PointCompareY comparatorY = new PointCompareY();
        int testCounter=0;

        DT dt=new DataStructure();
        for (int i=0;i<points.length;i++) 
	{
            dt.addPoint(points[i]);
        }
        String testName;
        int expected;
        int result;
        //0
        testName = "B"+testCounter;testCounter++;
        Arrays.sort(points,comparatorY);
        testExpectedPoints(testName, points, dt.getPointsInRangeRegAxis(0, 1000, false));

        //1
        Arrays.sort(points,comparatorY);
        testName = "B"+testCounter;testCounter++;
        testExpectedPoints(testName, points, dt.getPointsInRangeOppAxis(0, 1000, true));

        //2
        Arrays.sort(points,comparatorX);
        testName = "B"+testCounter;testCounter++;
        testExpectedPoints(testName, points, dt.getPointsInRangeRegAxis(-1, 1000, true));
        //3
        Arrays.sort(points,comparatorX);
        testName = "B"+testCounter;testCounter++;
        testExpectedPoints(testName, points, dt.getPointsInRangeOppAxis(0, 1000, false));


        //4
        Point[] resPoints = dt.nearestPair();
        double resultD = distance(resPoints[0],resPoints[1]);
        testName = "B"+testCounter;testCounter++;
        if(resultD<7.1)
            System.out.println("Test "+ testName+": passed :)");
        else
            System.out.println("Test "+ testName+": failed :)");
        testName = "B"+testCounter;testCounter++;
        if(dt.getDensity()<0.002)
            System.out.println("Test "+ testName+": passed :)");
        else
            System.out.println("Test "+ testName+": failed :)");
        testName = "B"+testCounter;testCounter++;
        testExpectedPoints(testName, new Point[]{new Point(500,734)}, new Point[]{dt.getMedian(true).getData()});
        testName = "B"+testCounter;testCounter++;
        testExpectedPoints(testName, new Point[]{new Point(34,500)}, new Point[]{dt.getMedian(false).getData()});

        //3

    }



    private static void testC() 
	{
	//Add your own tests here
    	}


    public static class PointCompareY implements Comparator<Point>
	 {

		@Override
		public int compare(Point point1, Point point2) 
		{
			if (point1.getY()<point2.getY()) return -1;
			return 1;
		}
		 
	 }
	
	public static class PointCompareX implements Comparator<Point>
	 {

		@Override
		public int compare(Point point1, Point point2) 
		{
			if (point1.getX()<point2.getX()) return -1;
			return 1;
		}
		
		 
	 }
		
	
	private static <T> void testExpected(String testName, T expected, T actual) 
	{
		if (!actual.equals(expected)) {
			System.out.println("Test " +testName+ ": wrong!\n expected=" + expected + ",\n actual=  " + actual);
		} else {
			System.out.println("Test " +testName+ ": passed :)");
		}
		
	}
	
	private static void testExpectedPoints(String testName, Point[] expectedPoints,	Point[] actualPoints) 
	{
		if (!equalAsLists(actualPoints,expectedPoints)) {
			if (equalAsSets(actualPoints,expectedPoints))
				System.out.println("Test " +testName+ ": order wrong!\n expected=" + Arrays.toString(expectedPoints) + ",\n actual=  " + Arrays.toString(actualPoints));
			else
				System.out.println("Test " +testName+ ": wrong!\n expected=" + Arrays.toString(expectedPoints) + ",\n actual=  " + Arrays.toString(actualPoints));
		} else {
			System.out.println("Test " +testName+ ": passed :)");
		}
	}


    //checks (inefficiently) that the sets are equal
	//NOTE: if one of the sets contains 2 points with same coordinates, might return erroneous result 
	private static boolean equalAsSets(Point[] actualPoints, Point[] expectedPoints) 
	{
		if (actualPoints.length!=expectedPoints.length) return false;
		for (int i=0;i<expectedPoints.length;i++)
		{
			boolean exists=false;
			for (int j=0;j<actualPoints.length;j++)
			{ 
				if (expectedPoints[i].equals(actualPoints[j])) exists=true;
			}
			if (!exists) return false;
		}
		return true;
	}

	//checks that the 2 lists of points are equal (order counts)
	private static boolean equalAsLists(Point[] actualPoints, Point[] expectedPoints) 
	{
		if (actualPoints.length!=expectedPoints.length) return false;
		for (int i=0;i<expectedPoints.length;i++)
		{
			if (!actualPoints[i].equals(expectedPoints[i])) return false;
		}
		return true;
	}

	//reading points from a file
	private static Point[] readPointsFile(String fileName) 
	{
		Vector<Point> points = new Vector<Point>();;
		BufferedReader input;
		FileReader fileReader;
		
		try {
			fileReader = new FileReader(fileName);
			input = new BufferedReader(fileReader);
			String line = null;
			String name;
			int x, y;

			while ((line = input.readLine()) != null) {
				String[] lineArray = line.split(";");
				name = lineArray[0];
				x = Integer.parseInt(lineArray[1]);
				y = Integer.parseInt(lineArray[2]);

				Point p = new Point(x, y, name);
				points.add(p);
			}
			input.close();
			fileReader.close();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		Point[] result = new Point[points.size()];
		points.toArray(result);
		return result;
	}

}
