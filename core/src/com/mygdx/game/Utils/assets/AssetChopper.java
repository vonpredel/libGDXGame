package com.mygdx.game.Utils.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.World.World;
import com.sun.imageio.plugins.common.ImageUtil;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;

public final class AssetChopper {

    private static final int SIZE = 64;

    private static final String blabal = "Body|Down|down_L_foot,1774,275,30,30\n" +
            "Body|Down|down_L_foot_tintable,1397,382,30,30\n" +
            "Body|Down|down_L_hand,1429,383,31,30\n" +
            "Body|Down|down_L_hand_tintable,1947,299,31,30\n" +
            "Body|Down|down_L_lower_arm,869,439,21,26\n" +
            "Body|Down|down_L_lower_arm_tintable,981,496,21,26\n" +
            "Body|Down|down_L_lower_leg,1697,370,16,17\n" +
            "Body|Down|down_L_lower_leg_tintable,1715,374,16,17\n" +
            "Body|Down|down_L_upper_arm,213,637,18,34\n" +
            "Body|Down|down_L_upper_arm_tintable,970,460,18,34\n" +
            "Body|Down|down_L_upper_leg,868,401,23,36\n" +
            "Body|Down|down_L_upper_leg_tintable,850,467,23,36\n" +
            "Body|Down|down_R_foot,959,401,30,30\n" +
            "Body|Down|down_R_foot_tintable,991,401,30,30\n" +
            "Body|Down|down_R_hand,893,399,31,30\n" +
            "Body|Down|down_R_hand_tintable,926,399,31,30\n" +
            "Body|Down|down_R_lower_arm,1004,494,21,26\n" +
            "Body|Down|down_R_lower_arm_tintable,992,524,21,26\n" +
            "Body|Down|down_R_lower_leg,1733,374,16,17\n" +
            "Body|Down|down_R_lower_leg_tintable,1491,413,16,17\n" +
            "Body|Down|down_R_upper_arm,918,631,18,34\n" +
            "Body|Down|down_R_upper_arm_tintable,938,631,18,34\n" +
            "Body|Down|down_R_upper_leg,875,467,23,36\n" +
            "Body|Down|down_R_upper_leg_tintable,879,529,23,36\n" +
            "Body|Down|down_body,718,981,52,41\n" +
            "Body|Down|down_body_tintable,772,981,52,41\n" +
            "Body|Down|down_chest,675,165,70,38\n" +
            "Body|Down|down_chest_tintable,576,941,70,38\n" +
            "Body_Down_down_eyebrows,672,931,54,8\n" +
            "Body_Down_down_eyebrows_tintable,153,549,54,8\n" +
            "Body|Down|down_eyes,106,1002,51,20\n" +
            "Body|Down|down_eyes_blue,1381,217,51,20\n" +
            "Body|Down|down_eyes_brown,1434,217,51,20\n" +
            "Body|Down|down_eyes_closed,1493,171,51,21\n" +
            "Body|Down|down_eyes_closed_tintable,1546,171,51,21\n" +
            "Body|Down|down_eyes_green,1489,194,51,20\n" +
            "Body|Down|down_eyes_tintable,1542,194,51,20\n" +
            "Body|Down|down_head,240,637,93,93\n" +
            "Body|Down|down_head_tintable,335,637,93,93\n" +
            "Body|Side|side_L_foot,1180,335,38,21\n" +
            "Body|Side|side_L_foot_tintable,1239,341,38,21\n" +
            "Body|Side|side_L_hand,1557,378,31,32\n" +
            "Body|Side|side_L_hand_tintable,1876,274,31,32\n" +
            "Body|Side|side_L_lower_arm,969,528,21,31\n" +
            "Body|Side|side_R_lower_arm,969,528,21,31\n" +
            "Body|Side|side_L_lower_arm_tintable,950,593,21,31\n" +
            "Body|Side|side_R_lower_arm_tintable,950,593,21,31\n" +
            "Body|Side|side_L_lower_leg,1237,399,18,20\n" +
            "Body|Side|side_L_lower_leg_tintable,1209,419,18,20\n" +
            "Body|Side|side_L_upper_arm,1935,261,27,36\n" +
            "Body|Side|side_L_upper_arm_tintable,1918,299,27,36\n" +
            "Body|Side|side_L_upper_leg,970,885,25,37\n" +
            "Body|Side|side_L_upper_leg_tintable,1273,366,25,37\n" +
            "Body|Side|side_R_foot,1331,303,38,37\n" +
            "Body|Side|side_R_foot_tintable,1371,307,38,37\n" +
            "Body|Side|side_R_hand,1366,346,32,30\n" +
            "Body|Up|side_R_hand,1366,346,32,30\n" +
            "Body|Side|side_R_hand_tintable,1363,378,32,30\n" +
            "Body|Up|side_R_hand_tintable,1363,378,32,30\n" +
            "Body|Side|side_R_lower_leg,1089,391,20,22\n" +
            "Body|Side|side_R_lower_leg_tintable,1155,393,20,22\n" +
            "Body|Side|side_R_upper_arm,1991,208,27,38\n" +
            "Body|Side|side_R_upper_arm_tintable,962,845,27,38\n" +
            "Body|Side|side_R_upper_leg,1029,358,28,38\n" +
            "Body|Side|side_R_upper_leg_tintable,1059,356,28,38\n" +
            "Body|Side|side_body,709,711,89,65\n" +
            "Body|Side|side_body_tintable,805,147,89,65\n" +
            "Body|Side|side_chest,1124,127,63,61\n" +
            "Body|Side|side_chest_tintable,1189,127,63,61\n" +
            "Body_Side_side_eyebrows,1733,275,39,30\n" +
            "Body_Side_side_eyebrows_tintable,1716,307,39,30\n" +
            "Body|Side|side_eyes,1220,335,17,18\n" +
            "Body|Side|side_eyes_blue,1962,177,17,18\n" +
            "Body|Side|side_eyes_brown,1962,197,17,18\n" +
            "Body|Side|side_eyes_closed,1249,421,17,18\n" +
            "Body|Side|side_eyes_closed_tintable,1047,494,17,18\n" +
            "Body|Side|side_eyes_green,1678,370,17,18\n" +
            "Body|Side|side_eyes_tintable,1677,390,17,18\n" +
            "Body|Side|side_head,1883,82,96,93\n" +
            "Body|Side|side_head_tintable,1783,179,96,93\n" +
            "Body|Up|up_L_foot,2020,208,26,35\n" +
            "Body|Up|up_L_foot_tintable,2020,245,26,35\n" +
            "Body|Up|up_L_hand,1301,337,28,32\n" +
            "Body|Up|up_L_hand_tintable,1300,371,28,32\n" +
            "Body|Up|up_L_lower_arm,1015,522,21,26\n" +
            "Body|Up|up_L_lower_arm_tintable,951,667,21,26\n" +
            "Body|Up|up_L_lower_leg,1189,416,18,21\n" +
            "Body|Up|up_L_lower_leg_tintable,1229,421,18,21\n" +
            "Body|Up|up_L_upper_arm,947,460,21,36\n" +
            "Body|Up|up_L_upper_arm_tintable,946,529,21,36\n" +
            "Body|Up|up_L_upper_leg,1881,179,25,41\n" +
            "Body|Up|up_L_upper_leg_tintable,1881,222,25,41\n" +
            "Body|Up|up_R_foot,2020,282,26,35\n" +
            "Body|Up|up_R_foot_tintable,2020,319,26,35\n" +
            "Body|Up|up_R_hand,990,433,28,32\n" +
            "Body|Up|up_R_hand_tintable,905,495,28,32\n" +
            "Body|Up|up_R_lower_arm,952,733,21,26\n" +
            "Body|Up|up_R_lower_arm_tintable,952,761,21,26\n" +
            "Body|Up|up_R_lower_leg,1027,494,18,21\n" +
            "Body|Up|up_R_lower_leg_tintable,1035,471,18,21\n" +
            "Body|Up|up_R_upper_arm,904,593,21,36\n" +
            "Body|Up|up_R_upper_arm_tintable,927,593,21,36\n" +
            "Body|Up|up_R_upper_leg,1908,177,25,41\n" +
            "Body|Up|up_R_upper_leg_tintable,1935,177,25,41\n" +
            "Body|Up|up_body,828,845,52,46\n" +
            "Body|Up|up_body_tintable,828,893,52,46\n" +
            "Body|Up|up_chest,443,726,72,49\n" +
            "Body|Up|up_chest_tintable,889,232,72,49\n" +
            "Body|Up|up_head,405,552,93,83\n" +
            "Body|Up|up_head_tintable,516,635,93,83\n" +
            "Equipment Down|Armours|armour_1_down_body,41,618,66,53\n" +
            "Equipment Down|Armours|armour_1_down_chest,977,191,70,42\n" +
            "Equipment Down|Armours|armour_2_down_body,742,102,58,49\n" +
            "Equipment Down|Armours|armour_2_down_chest,240,732,72,45\n" +
            "Equipment Down Belts belt_1_down,1381,191,52,24\n" +
            "Equipment Down Belts belt_2_down,1595,207,46,19\n" +
            "Equipment Down Belts belt_3_down,1643,207,46,21\n" +
            "Equipment Down Belts belt_4_down,741,371,52,30\n" +
            "Equipment Down|Gloves|brace_down_L_hand,915,819,31,24\n" +
            "Equipment Down|Gloves|brace_down_R_hand,893,431,31,24\n" +
            "Equipment Down|Gloves|fingerless_down_L_hand,851,281,35,42\n" +
            "Equipment Down|Gloves|fingerless_down_L_hand_tintable,1257,172,35,42\n" +
            "Equipment Down|Gloves|fingerless_down_R_hand,1257,216,35,42\n" +
            "Equipment Down|Gloves|fingerless_down_R_hand_tintable,1257,260,35,42\n" +
            "Equipment Down|Gloves|gauntlet_down_L_hand,1294,209,35,42\n" +
            "Equipment Down|Gloves|gauntlet_down_R_hand,1294,253,35,42\n" +
            "Equipment Down|Gloves|glove_down_L_hand,1331,233,35,42\n" +
            "Equipment Down|Gloves|glove_down_L_hand_tintable,927,978,35,42\n" +
            "Equipment Down|Gloves|glove_down_R_hand,849,357,35,42\n" +
            "Equipment Down|Gloves|glove_down_R_hand_tintable,915,733,35,42\n" +
            "Equipment Down|Gloves|ring_down_L_hand,123,446,13,10\n" +
            "Equipment Down|Gloves|ring_down_L_hand_tintable,138,446,13,10\n" +
            "Equipment Down|Gloves|ring_down_L_hand_tintable_tintable,138,446,13,10\n" +
            "Equipment Down|Gloves|ring_down_R_hand,445,860,13,10\n" +
            "Equipment Down|Gloves|ring_down_R_hand_tintable,716,845,13,10\n" +
            "Equipment Down|Gloves|ring_down_R_hand_tintable_tintable,716,845,13,10\n" +
            "Equipment Down|Pants|cloth_down_L_lower_leg,853,567,16,17\n" +
            "Equipment Down|Pants|cloth_down_L_lower_leg_tintable,871,567,16,17\n" +
            "Equipment Down|Pants|cloth_down_L_upper_leg,1011,279,24,36\n" +
            "Equipment Down|Pants|cloth_down_L_upper_leg_tintable,1866,308,24,36\n" +
            "Equipment Down|Pants|cloth_down_R_lower_leg,889,567,16,17\n" +
            "Equipment Down|Pants|cloth_down_R_lower_leg_tintable,1199,397,16,17\n" +
            "Equipment Down|Pants|cloth_down_R_upper_leg,1892,308,24,36\n" +
            "Equipment Down|Pants|cloth_down_R_upper_leg_tintable,1909,260,24,36\n" +
            "Equipment Down|Pants|cloth_down_hip,958,302,44,17\n" +
            "Equipment Down|Pants|cloth_down_hip_tintable,1193,297,44,17\n" +
            "Equipment Down|Pants|scalemail_down_L_lower_leg,1509,413,16,17\n" +
            "Equipment Down|Pants|scalemail_down_L_upper_leg,853,529,24,36\n" +
            "Equipment Down|Pants|scalemail_down_R_lower_leg,1527,413,16,17\n" +
            "Equipment Down|Pants|scalemail_down_R_upper_leg,900,457,24,36\n" +
            "Equipment Down|Pants|scalemail_down_hip,1193,316,44,17\n" +
            "Equipment Down|Shirts|cloth_down_L_lower_arm,1111,394,20,21\n" +
            "Equipment Up|Shirts|cloth_up_R_lower_arm,1111,394,20,21\n" +
            "Equipment Down|Shirts|cloth_down_L_lower_arm_tintable,1133,394,20,21\n" +
            "Equipment Up|Shirts|cloth_up_R_lower_arm_tintable,1133,394,20,21\n" +
            "Equipment Down|Shirts|cloth_down_L_upper_arm,1574,216,19,36\n" +
            "Equipment Down|Shirts|cloth_down_L_upper_arm_tintable,1280,304,19,36\n" +
            "Equipment Down|Shirts|cloth_down_R_lower_arm,1177,393,20,21\n" +
            "Equipment Up|Shirts|cloth_up_L_lower_arm,1177,393,20,21\n" +
            "Equipment Down|Shirts|cloth_down_R_lower_arm_tintable,1023,425,20,21\n" +
            "Equipment Up|Shirts|cloth_up_L_lower_arm_tintable,1023,425,20,21\n" +
            "Equipment Down|Shirts|cloth_down_R_upper_arm,1670,251,19,36\n" +
            "Equipment Down|Shirts|cloth_down_R_upper_arm_tintable,1123,356,19,36\n" +
            "Equipment Down|Shirts|scalemail_down_L_lower_arm,1045,422,20,21\n" +
            "Equipment Up|Shirts|scalemail_up_R_lower_arm,1045,422,20,21\n" +
            "Equipment Down|Shirts|scalemail_down_L_upper_arm,904,529,19,36\n" +
            "Equipment Down|Shirts|scalemail_down_R_lower_arm,1067,420,20,21\n" +
            "Equipment Up|Shirts|scalemail_up_L_lower_arm,1067,420,20,21\n" +
            "Equipment Down|Shirts|scalemail_down_R_upper_arm,925,529,19,36\n" +
            "Equipment Down|Shirts|scalemail_down_body,648,941,56,38\n" +
            "Equipment Down|Shirts|scalemail_down_chest,1124,190,70,42\n" +
            "Equipment Down|Shirts|shirt_down_L_upper_arm,1591,296,25,41\n" +
            "Equipment Down|Shirts|shirt_down_L_upper_arm_tintable,1651,293,25,41\n" +
            "Equipment Down|Shirts|shirt_down_R_upper_arm,961,931,25,41\n" +
            "Equipment Down|Shirts|shirt_down_R_upper_arm_tintable,964,974,25,41\n" +
            "Equipment Down|Shirts|shirt_down_body,768,586,66,47\n" +
            "Equipment Down|Shirts|shirt_down_body_tintable,836,586,66,47\n" +
            "Equipment Down|Shirts|shirt_down_chest,1050,113,72,42\n" +
            "Equipment Down|Shirts|shirt_down_chest_tintable,1050,157,72,42\n" +
            "Equipment Down|Shirts|tunic_down_body,800,697,56,48\n" +
            "Equipment Down|Shirts|tunic_down_body_tintable,800,747,56,48\n" +
            "Equipment Down|Shirts|tunic_down_chest,574,981,70,41\n" +
            "Equipment Down|Shirts|tunic_down_chest_tintable,646,981,70,41\n" +
            "Equipment Down|Shoes|armour_down_L_foot,926,366,33,31\n" +
            "Equipment Down|Shoes|armour_down_L_lower_leg,1279,342,20,22\n" +
            "Equipment Down|Shoes|armour_down_R_foot,1079,289,33,31\n" +
            "Equipment Down|Shoes|armour_down_R_lower_leg,847,443,20,22\n" +
            "Equipment Down|Shoes|boot_down_L_foot,2016,76,30,31\n" +
            "Equipment Down|Shoes|boot_down_L_foot_tintable,2016,109,30,31\n" +
            "Equipment Down|Shoes|boot_down_R_foot,2016,142,30,31\n" +
            "Equipment Down|Shoes|boot_down_R_foot_tintable,2016,175,30,31\n" +
            "Equipment Down|Shoes|boot_flap_down_L_lower_leg,907,567,23,24\n" +
            "Equipment Up|Shoes|boot_flap_up_R_lower_leg,907,567,23,24\n" +
            "Equipment Down|Shoes|boot_flap_down_L_lower_leg_tintable,932,567,23,24\n" +
            "Equipment Up|Shoes|boot_flap_up_R_lower_leg_tintable,932,567,23,24\n" +
            "Equipment Down|Shoes|boot_flap_down_R_lower_leg,957,567,23,24\n" +
            "Equipment Up|Shoes|boot_flap_up_L_lower_leg,957,567,23,24\n" +
            "Equipment Down|Shoes|boot_flap_down_R_lower_leg_tintable,926,694,23,24\n" +
            "Equipment Up|Shoes|boot_flap_up_L_lower_leg_tintable,926,694,23,24\n" +
            "Equipment Down|Shoes|sandals_down_L_foot,1089,356,32,33\n" +
            "Equipment Down|Shoes|sandals_down_L_foot_tintable,1144,358,32,33\n" +
            "Equipment Down|Shoes|sandals_down_R_foot,1178,358,32,33\n" +
            "Equipment Down|Shoes|sandals_down_R_foot_tintable,1239,364,32,33\n" +
            "Equipment Down|Shoulder Pads|shoulderpad_1_down_L,1806,274,33,32\n" +
            "Equipment Down|Shoulder Pads|shoulderpad_1_down_R,1841,274,33,32\n" +
            "Equipment Down|Shoulder Pads|shoulderpad_2_down_L,528,233,38,41\n" +
            "Equipment Down|Shoulder Pads|shoulderpad_2_down_R,826,981,38,41\n" +
            "Equipment Down|Shoulder Pads|shoulderpad_3_down_L,1534,255,36,39\n" +
            "Equipment Down|Shoulder Pads|shoulderpad_3_down_R,1526,296,36,39\n" +
            "Equipment Down|Shoulder Pads|shoulderpad_4_down_L,314,732,41,45\n" +
            "Equipment Down|Shoulder Pads|shoulderpad_4_down_R,357,732,41,45\n" +
            "Equipment Up|Shoulder Pads|shoulderpad_4_up_L,357,732,41,45\n" +
            "Equipment Side|Armours|armour_1_side_body,657,715,50,70\n" +
            "Equipment Side|Armours|armour_1_side_chest,993,129,55,60\n" +
            "Equipment Side|Armours|armour_2_side_body,479,481,51,69\n" +
            "Equipment Side|Armours|armour_2_side_chest,794,281,55,60\n" +
            "Equipment Side Belts belt_1_side,858,781,55,37\n" +
            "Equipment Side Belts belt_2_side,1335,204,44,27\n" +
            "Equipment Side Belts belt_3_side,1599,178,47,27\n" +
            "Equipment Side Belts belt_4_side,882,845,51,43\n" +
            "Equipment Side|Gloves|brace_side_L_hand,1981,187,33,19\n" +
            "Equipment Side|Gloves|brace_side_R_hand,528,276,33,24\n" +
            "Equipment Side|Gloves|fingerless_side_L_hand_,1419,269,38,37\n" +
            "Equipment Side|Gloves|fingerless_side_L_hand__tintable,1534,216,38,37\n" +
            "Equipment Side|Gloves|fingerless_side_R_hand,960,321,37,38\n" +
            "Equipment Side|Gloves|fingerless_side_R_hand_tintable,961,361,37,38\n" +
            "Equipment Side|Gloves|gauntlet_side_L_hand,1411,308,37,35\n" +
            "Equipment Side|Gloves|gauntlet_side_R_hand,1009,85,39,42\n" +
            "Equipment Side|Gloves|glove_side_L_hand,1450,311,37,35\n" +
            "Equipment Side|Gloves|glove_side_L_hand_tintable,1495,257,37,35\n" +
            "Equipment Side|Gloves|glove_side_R_hand,886,934,39,42\n" +
            "Equipment Side|Gloves|glove_side_R_hand_tintable,1294,165,39,42\n" +
            "Equipment Side|Gloves|ring_side_L_hand,303,969,15,10\n" +
            "Equipment Side|Gloves|ring_side_L_hand_tintable,320,969,15,10\n" +
            "Equipment Side|Gloves|ring_side_R_hand,896,147,13,14\n" +
            "Equipment Side|Gloves|ring_side_R_hand_tintable,977,169,13,14\n" +
            "Equipment Side|Pants|cloth_side_L_lower_leg,1109,417,18,20\n" +
            "Equipment Side|Pants|cloth_side_L_lower_leg_tintable,1129,417,18,20\n" +
            "Equipment Side|Pants|cloth_side_L_upper_leg,1564,339,25,37\n" +
            "Equipment Side|Pants|cloth_side_L_upper_leg_tintable,1591,339,25,37\n" +
            "Equipment Side|Pants|cloth_side_R_lower_leg,1023,401,20,22\n" +
            "Equipment Side|Pants|cloth_side_R_lower_leg_tintable,1045,398,20,22\n" +
            "Equipment Side|Pants|cloth_side_R_upper_leg,1301,297,28,38\n" +
            "Equipment Side|Pants|cloth_side_R_upper_leg_tintable,1618,335,28,38\n" +
            "Equipment Side|Pants|cloth_side_hip,1648,178,41,27\n" +
            "Equipment Side|Pants|cloth_side_hip_tintable,1739,227,41,27\n" +
            "Equipment Side|Pants|scalemail_side_L_lower_leg,1217,397,18,20\n" +
            "Equipment Side|Pants|scalemail_side_L_upper_leg,1212,358,25,37\n" +
            "Equipment Side|Pants|scalemail_side_R_lower_leg,1067,396,20,22\n" +
            "Equipment Side|Pants|scalemail_side_R_upper_leg,1648,336,28,38\n" +
            "Equipment Side|Pants|scalemail_side_hip,1691,270,40,27\n" +
            "Equipment Side|Shirts|cloth_side_L_lower_arm,1472,239,21,28\n" +
            "Equipment Side|Shirts|cloth_side_L_lower_arm_tintable,958,498,21,28\n" +
            "Equipment Side|Shirts|cloth_side_L_upper_arm,1590,378,27,36\n" +
            "Equipment Side|Shirts|cloth_side_L_upper_arm_tintable,1619,375,27,36\n" +
            "Equipment Side|Shirts|cloth_side_R_lower_arm,958,626,21,28\n" +
            "Equipment Side|Shirts|cloth_side_R_lower_arm_tintable,973,593,21,28\n" +
            "Equipment Side|Shirts|cloth_side_R_upper_arm,999,321,27,38\n" +
            "Equipment Side|Shirts|cloth_side_R_upper_arm_tintable,1000,361,27,38\n" +
            "Equipment Side|Shirts|scalemail_side_L_lower_arm,981,623,21,27\n" +
            "Equipment Side|Shirts|scalemail_side_L_upper_arm,1648,376,27,36\n" +
            "Equipment Side|Shirts|scalemail_side_R_lower_arm,935,498,21,29\n" +
            "Equipment Side|Shirts|scalemail_side_R_upper_arm,1908,220,27,38\n" +
            "Equipment Side|Shirts|scalemail_side_body,800,529,51,55\n" +
            "Equipment Side|Shirts|scalemail_side_chest,742,469,52,60\n" +
            "Equipment Side|Shirts|shirt_side_L_upper_arm,1028,319,29,37\n" +
            "Equipment Side|Shirts|shirt_side_L_upper_arm_tintable,1114,317,29,37\n" +
            "Equipment Side|Shirts|shirt_side_R_upper_arm,1637,251,31,40\n" +
            "Equipment Side|Shirts|shirt_side_R_upper_arm_tintable,1618,293,31,40\n" +
            "Equipment Side|Shirts|shirt_side_body,754,205,49,64\n" +
            "Equipment Side|Shirts|shirt_side_body_tintable,742,403,49,64\n" +
            "Equipment Side|Shirts|shirt_side_chest,795,343,52,60\n" +
            "Equipment Side|Shirts|shirt_side_chest_tintable,793,405,52,60\n" +
            "Equipment Side|Shirts|tunic_side_body,426,481,51,67\n" +
            "Equipment Side|Shirts|tunic_side_body_tintable,741,302,51,67\n" +
            "Equipment Side|Shirts|tunic_side_chest,796,467,52,60\n" +
            "Equipment Side|Shirts|tunic_side_chest_tintable,796,635,52,60\n" +
            "Equipment Side|Shoes|armour_side_L_foot,1331,277,43,24\n" +
            "Equipment Side|Shoes|armour_side_L_lower_leg,883,505,20,22\n" +
            "Equipment Up|Shoes|armour_up_R_lower_leg,883,505,20,22\n" +
            "Equipment Side|Shoes|armour_side_R_foot,851,325,36,30\n" +
            "Equipment Side|Shoes|armour_side_R_lower_leg,948,819,22,24\n" +
            "Equipment Side|Shoes|boot_flap_side_L_lower_leg,951,695,23,23\n" +
            "Equipment Side|Shoes|boot_flap_side_L_lower_leg_tintable,952,789,23,23\n" +
            "Equipment Side|Shoes|boot_flap_side_R_lower_leg,990,467,23,25\n" +
            "Equipment Side|Shoes|boot_flap_side_R_lower_leg_tintable,926,667,23,25\n" +
            "Equipment Side|Shoes|boot_side_L_foot,590,559,39,25\n" +
            "Equipment Side|Shoes|boot_side_L_foot_tintable,676,275,39,25\n" +
            "Equipment Side|Shoes|boot_side_R_foot,1757,307,34,31\n" +
            "Equipment Side|Shoes|boot_side_R_foot_tintable,1752,340,34,31\n" +
            "Equipment Side|Shoes|sandals_side_L_foot,1037,289,40,28\n" +
            "Equipment Side|Shoes|sandals_side_L_foot_tintable,1151,275,40,28\n" +
            "Equipment Side|Shoes|sandals_side_R_foot,1678,335,35,33\n" +
            "Equipment Side|Shoes|sandals_side_R_foot_tintable,1715,339,35,33\n" +
            "Equipment Side|Shoulder Pads|shoulderpad_1_side_L,1678,299,36,34\n" +
            "Equipment Side|Shoulder Pads|shoulderpad_1_side_R,889,324,39,33\n" +
            "Equipment Side|Shoulder Pads|shoulderpad_2_side_L,1793,308,36,28\n" +
            "Equipment Side|Shoulder Pads|shoulderpad_2_side_R,1335,165,44,37\n" +
            "Equipment Side|Shoulder Pads|shoulderpad_3_side_L,1239,304,39,35\n" +
            "Equipment Side|Shoulder Pads|shoulderpad_3_side_R,1495,216,37,39\n" +
            "Equipment Side|Shoulder Pads|shoulderpad_4_side_L,1254,127,37,43\n" +
            "Equipment Side|Shoulder Pads|shoulderpad_4_side_R,1376,269,41,36\n" +
            "Equipment Up|Armours|armour_1_up_body,346,969,66,53\n" +
            "Equipment Up|Armours|armour_1_up_chest,1037,245,70,42\n" +
            "Equipment Up|Armours|armour_2_up_body,414,963,58,50\n" +
            "Equipment Up|Armours|armour_2_up_chest,1133,83,73,42\n" +
            "Equipment Up Belts belt_1_up,857,820,52,23\n" +
            "Equipment Up Belts belt_2_up,1691,250,46,18\n" +
            "Equipment Up Belts belt_3_up,1643,230,46,19\n" +
            "Equipment Up Belts belt_4_up,1435,191,52,24\n" +
            "Equipment Up|Gloves|brace_up_L_hand,991,1000,31,22\n" +
            "Equipment Up|Gloves|brace_up_R_hand,850,505,31,22\n" +
            "Equipment Up|Gloves|fingerless_up_L_hand,2016,2,30,35\n" +
            "Equipment Up|Gloves|fingerless_up_L_hand_tintable,2016,39,30,35\n" +
            "Equipment Up|Gloves|fingerless_up_R_hand,926,431,30,25\n" +
            "Equipment Up|Gloves|fingerless_up_R_hand_tintable,958,433,30,25\n" +
            "Equipment Up|Gloves|gauntlet_up_L_hand,893,978,32,43\n" +
            "Equipment Up|Gloves|gauntlet_up_R_hand,927,934,32,42\n" +
            "Equipment Up|Gloves|glove_up_L_hand,915,777,35,40\n" +
            "Equipment Up|Gloves|glove_up_L_hand_tintable,1114,275,35,40\n" +
            "Equipment Up|Gloves|glove_up_R_hand,1489,294,35,40\n" +
            "Equipment Up|Gloves|glove_up_R_hand_tintable,1489,336,35,40\n" +
            "Equipment Up|Gloves|ring_up_L_hand,926,720,15,11\n" +
            "Equipment Up|Gloves|ring_up_L_hand_tintable,943,720,15,11\n" +
            "Equipment Up|Gloves|ring_up_R_hand,960,720,15,11\n" +
            "Equipment Up|Gloves|ring_up_R_hand_tintable,159,880,15,11\n" +
            "Equipment Up|Pants|cloth_up_L_lower_leg,405,529,18,21\n" +
            "Equipment Up|Pants|cloth_up_L_lower_leg_tintable,1089,415,18,21\n" +
            "Equipment Up|Pants|cloth_up_L_upper_leg,399,441,25,42\n" +
            "Equipment Up|Pants|cloth_up_L_upper_leg_tintable,399,485,25,42\n" +
            "Equipment Up|Pants|cloth_up_R_lower_leg,1149,417,18,21\n" +
            "Equipment Up|Pants|cloth_up_R_lower_leg_tintable,1020,448,18,21\n" +
            "Equipment Up|Pants|cloth_up_R_upper_leg,866,981,25,41\n" +
            "Equipment Up|Pants|cloth_up_R_upper_leg_tintable,935,845,25,41\n" +
            "Equipment Up|Pants|cloth_up_hip,1595,228,46,21\n" +
            "Equipment Up|Pants|cloth_up_hip_tintable,1691,227,46,21\n" +
            "Equipment Up|Pants|scalemail_up_L_lower_leg,1015,471,18,21\n" +
            "Equipment Up|Pants|scalemail_up_L_upper_leg,943,888,25,41\n" +
            "Equipment Up|Pants|scalemail_up_R_lower_leg,1169,417,18,21\n" +
            "Equipment Up|Pants|scalemail_up_R_upper_leg,1564,296,25,41\n" +
            "Equipment Up|Pants|scalemail_up_hip,963,279,46,21\n" +
            "Equipment Up|Shirts|cloth_up_L_upper_arm,1429,345,19,36\n" +
            "Equipment Up|Shirts|cloth_up_L_upper_arm_tintable,988,924,19,36\n" +
            "Equipment Up|Shirts|cloth_up_R_upper_arm,991,962,19,36\n" +
            "Equipment Up|Shirts|cloth_up_R_upper_arm_tintable,847,405,19,36\n" +
            "Equipment Up|Shirts|scalemail_up_L_upper_arm,926,458,19,35\n" +
            "Equipment Up|Shirts|scalemail_up_R_upper_arm,1059,319,18,35\n" +
            "Equipment Up|Shirts|scalemail_up_body,828,941,56,38\n" +
            "Equipment Up|Shirts|scalemail_up_chest,1208,83,72,42\n" +
            "Equipment Up|Shirts|shirt_up_L_upper_arm,1572,255,25,39\n" +
            "Equipment Up|Shirts|shirt_up_L_upper_arm_tintable,1937,220,25,39\n" +
            "Equipment Up|Shirts|shirt_up_R_upper_arm,1964,217,25,39\n" +
            "Equipment Up|Shirts|shirt_up_R_upper_arm_tintable,1964,258,25,39\n" +
            "Equipment Up|Shirts|shirt_up_body,850,635,66,47\n" +
            "Equipment Up|Shirts|shirt_up_body_tintable,858,684,66,47\n" +
            "Equipment Up|Shirts|shirt_up_chest,1049,201,72,42\n" +
            "Equipment Up|Shirts|shirt_up_chest_tintable,963,235,72,42\n" +
            "Equipment Up|Shirts|tunic_up_body,747,153,56,50\n" +
            "Equipment Up|Shirts|tunic_up_body_tintable,742,531,56,50\n" +
            "Equipment Up|Shirts|tunic_up_chest,198,981,72,41\n" +
            "Equipment Up|Shirts|tunic_up_chest_tintable,272,981,72,41\n" +
            "Equipment Up|Shoes|armour_up_L_foot,930,324,28,40\n" +
            "Equipment Up|Shoes|armour_up_L_lower_leg,992,552,20,22\n" +
            "Equipment Up|Shoes|armour_up_R_foot,1459,269,28,40\n" +
            "Equipment Up|Shoes|boot_up_L_foot,1991,248,27,34\n" +
            "Equipment Up|Shoes|boot_up_L_foot_tintable,1991,284,27,34\n" +
            "Equipment Up|Shoes|boot_up_R_foot,1400,346,27,34\n" +
            "Equipment Up|Shoes|boot_up_R_foot_tintable,1462,383,27,34\n" +
            "Equipment Up|Shoes|sandals_up_L_foot,1330,372,31,33\n" +
            "Equipment Up|Shoes|sandals_up_L_foot_tintable,1450,348,31,33\n" +
            "Equipment Up|Shoes|sandals_up_R_foot,1491,378,31,33\n" +
            "Equipment Up|Shoes|sandals_up_R_foot_tintable,1524,378,31,33\n" +
            "Equipment Up|Shoulder Pads|shoulderpad_1_up_L,1079,322,33,32\n" +
            "Equipment Up|Shoulder Pads|shoulderpad_1_up_R,1145,324,33,32\n" +
            "Equipment Up|Shoulder Pads|shoulderpad_2_up_L,706,941,38,38\n" +
            "Equipment Up|Shoulder Pads|shoulderpad_2_up_R,886,359,38,38\n" +
            "Equipment Up|Shoulder Pads|shoulderpad_3_up_L,1526,337,36,39\n" +
            "Equipment Up|Shoulder Pads|shoulderpad_3_up_R,1599,251,36,39\n" +
            "Equipment Up|Shoulder Pads|shoulderpad_4_up_R,400,732,41,45\n" +
            "Head Objects|Facial Hair|facial_hair_1_down,1739,256,40,17\n" +
            "Head Objects|Facial Hair|facial_hair_1_down_tintable,1151,305,40,17\n" +
            "Head Objects|Facial Hair|facial_hair_1_side,159,1002,23,20\n" +
            "Head Objects|Facial Hair|facial_hair_1_side_tintable,1918,337,23,20\n" +
            "Head Objects|Facial Hair|facial_hair_2_down,1381,165,54,24\n" +
            "Head Objects|Facial Hair|facial_hair_2_down_tintable,1437,165,54,24\n" +
            "Head Objects|Facial Hair|facial_hair_2_side,717,275,32,25\n" +
            "Head Objects|Facial Hair|facial_hair_2_side_tintable,751,275,32,25\n" +
            "Head Objects|Facial Hair|facial_hair_3_down,1368,239,50,28\n" +
            "Head Objects|Facial Hair|facial_hair_3_down_tintable,1420,239,50,28\n" +
            "Head Objects|Facial Hair|facial_hair_3_side,1831,308,33,28\n" +
            "Head Objects|Facial Hair|facial_hair_3_side_tintable,1331,342,33,28\n" +
            "Head Objects|Facial Hair|facial_hair_4_down,1705,161,76,64\n" +
            "Head Objects|Facial Hair|facial_hair_4_down_tintable,750,915,76,64\n" +
            "Head Objects|Facial Hair|facial_hair_4_side,800,797,55,46\n" +
            "Head Objects|Facial Hair|facial_hair_4_side_tintable,858,733,55,46\n" +
            "Head Objects|Facial Hair|facial_hair_5_down,638,787,76,70\n" +
            "Head Objects|Facial Hair|facial_hair_5_down_tintable,672,859,76,70\n" +
            "Head Objects|Facial Hair|facial_hair_5_side,1196,190,59,42\n" +
            "Head Objects|Facial Hair|facial_hair_5_side_tintable,882,890,59,42\n" +
            "Head Objects|Facial Hair|facial_hair_6_down,750,845,76,68\n" +
            "Head Objects|Facial Hair|facial_hair_6_down_tintable,676,205,76,68\n" +
            "Head Objects|Facial Hair|facial_hair_6_side,517,720,68,55\n" +
            "Head Objects|Facial Hair|facial_hair_6_side_tintable,587,720,68,55\n" +
            "Head Objects|Hair|hair_1_down,912,115,79,52\n" +
            "Head Objects|Hair|hair_1_side,896,169,79,61\n" +
            "Head Objects|Hair|hair_1_up,1705,81,76,78\n" +
            "Head Objects|Hair|hair_2_down,716,778,82,65\n" +
            "Head Objects|Hair|hair_2_down_tintable,805,214,82,65\n" +
            "Head Objects|Hair|hair_2_side,445,777,95,81\n" +
            "Head Objects|Hair|hair_2_side_tintable,479,860,95,81\n" +
            "Head Objects|Hair|hair_2_up,393,874,84,87\n" +
            "Head Objects|Hair|hair_2_up_tintable,430,637,84,87\n" +
            "Head Objects|Hair|hair_3_down,479,943,93,78\n" +
            "Head Objects|Hair|hair_3_down_tintable,611,635,93,78\n" +
            "Head Objects|Hair|hair_3_side,311,2,120,110\n" +
            "Head Objects|Hair|hair_3_side_tintable,433,2,120,110\n" +
            "Head Objects|Hair|hair_3_up,209,441,93,98\n" +
            "Head Objects|Hair|hair_3_up_tintable,304,441,93,98\n" +
            "Head Objects|Hair|hair_4_down,500,559,88,74\n" +
            "Head Objects|Hair|hair_4_down_tintable,706,635,88,74\n" +
            "Head Objects|Hair|hair_4_side,542,777,94,80\n" +
            "Head Objects|Hair|hair_4_side_tintable,576,859,94,80\n" +
            "Head Objects|Hair|hair_4_up,264,779,88,93\n" +
            "Head Objects|Hair|hair_4_up_tintable,303,874,88,93\n" +
            "Head Objects|Hair|hair_5_down,1298,77,101,86\n" +
            "Head Objects|Hair|hair_5_down_tintable,1401,77,101,86\n" +
            "Head Objects|Hair|hair_5_side,532,466,103,91\n" +
            "Head Objects|Hair|hair_5_side_tintable,637,493,103,91\n" +
            "Head Objects|Hair|hair_5_up,325,335,99,104\n" +
            "Head Objects|Hair|hair_5_up_tintable,427,215,99,104\n" +
            "Head Objects|Hair|hair_6_down,159,779,103,99\n" +
            "Head Objects|Hair|hair_6_down_tintable,198,880,103,99\n" +
            "Head Objects|Hair|hair_6_side,421,114,105,99\n" +
            "Head Objects|Hair|hair_6_side_tintable,568,104,105,99\n" +
            "Head Objects|Hair|hair_6_up,2,912,102,109\n" +
            "Head Objects|Hair|hair_6_up_tintable,317,114,102,109\n" +
            "Head Objects|Hair|hair_7_down,1605,81,98,95\n" +
            "Head Objects|Hair|hair_7_down_tintable,1783,82,98,95\n" +
            "Head Objects|Hair|hair_7_side,535,302,101,99\n" +
            "Head Objects|Hair|hair_7_side_tintable,638,302,101,99\n" +
            "Head Objects|Hair|hair_7_up,112,567,99,104\n" +
            "Head Objects|Hair|hair_7_up_tintable,139,673,99,104\n" +
            "Head Objects|Hair|headwear_hair_long_down,220,261,108,57\n" +
            "Head Objects|Hair|headwear_hair_long_down_tintable,802,88,108,57\n" +
            "Head Objects|Hair|headwear_hair_long_side,678,102,62,61\n" +
            "Head Objects|Hair|headwear_hair_long_side_tintable,1193,234,62,61\n" +
            "Head Objects|Hair|headwear_hair_long_up,426,418,104,61\n" +
            "Head Objects|Hair|headwear_hair_long_up_tintable,535,403,104,61\n" +
            "Head Objects|Hair|headwear_hair_short_down,926,85,81,28\n" +
            "Head Objects|Hair|headwear_hair_short_down_tintable,1050,83,81,28\n" +
            "Head Objects|Hair|headwear_hair_short_side,1123,234,68,39\n" +
            "Head Objects|Hair|headwear_hair_short_side_tintable,888,283,68,39\n" +
            "Head Objects|Hair|headwear_hair_short_up,590,586,87,47\n" +
            "Head Objects|Hair|headwear_hair_short_up_tintable,679,586,87,47\n" +
            "Head Objects|Headwear|cap_1_down,1050,2,122,79\n" +
            "Head Objects|Headwear|cap_1_down_tintable,1174,2,122,79\n" +
            "Head Objects|Headwear|cap_1_side,1788,2,112,78\n" +
            "Head Objects|Headwear|cap_1_side_tintable,1902,2,112,78\n" +
            "Head Objects|Headwear|cap_1_up,1552,2,116,77\n" +
            "Head Objects|Headwear|cap_1_up_tintable,1670,2,116,77\n" +
            "Head Objects|Headwear|cap_2_down,220,151,95,108\n" +
            "Head Objects|Headwear|cap_2_down_tintable,330,225,95,108\n" +
            "Head Objects|Headwear|cap_2_side,568,205,106,95\n" +
            "Head Objects|Headwear|cap_2_side_tintable,427,321,106,95\n" +
            "Head Objects|Headwear|cap_2_up,213,541,94,94\n" +
            "Head Objects|Headwear|cap_2_up_tintable,309,541,94,94\n" +
            "Head Objects|Headwear|cap_3_down,2,793,93,117\n" +
            "Head Objects|Headwear|cap_3_down_tintable,170,322,93,117\n" +
            "Head Objects|Headwear|cap_3_side,1298,2,125,73\n" +
            "Head Objects|Headwear|cap_3_side_tintable,1425,2,125,73\n" +
            "Head Objects|Headwear|cap_3_up,641,403,99,88\n" +
            "Head Objects|Headwear|cap_3_up_tintable,1504,81,99,88\n" +
            "Head Objects|Headwear|helm_1_down,220,2,89,147\n" +
            "Head Objects|Headwear|helm_1_side,2,303,119,155\n" +
            "Head Objects|Headwear|helm_1_up,44,673,93,109\n" +
            "Head Objects|Headwear|helm_2_down,926,2,122,81\n" +
            "Head Objects|Headwear|helm_2_side,354,779,89,93\n" +
            "Head Objects|Headwear|helm_2_up,802,2,122,84\n" +
            "Head Objects|Headwear|helm_3_down,678,2,122,98\n" +
            "Head Objects|Headwear|helm_3_side,106,895,90,105\n" +
            "Head Objects|Headwear|helm_3_up,555,2,121,100\n" +
            "Weapon Objects|bow_1,2,673,40,118\n" +
            "Weapon Objects|bow_2,123,303,45,141\n" +
            "Weapon Objects|bow_effect,528,114,38,117\n" +
            "Weapon Objects|bow_sprite,109,618,1,1\n" +
            "Equipment Down|Armours|sprite_down_body,109,618,1,1\n" +
            "Equipment Down|Armours|sprite_down_chest,109,618,1,1\n" +
            "Equipment Down|Belts|sprite_down,109,618,1,1\n" +
            "Equipment Down|Gloves|sprite_down_L_hand,109,618,1,1\n" +
            "Equipment Down|Gloves|sprite_down_R_hand,109,618,1,1\n" +
            "Equipment Down|Pants|sprite_down_L_upper_leg,109,618,1,1\n" +
            "Equipment Down|Pants|sprite_down_R_upper_leg,109,618,1,1\n" +
            "Equipment Down|Pants|sprite_down_hip,109,618,1,1\n" +
            "Equipment Down|Shirts|sprite_down_L_lower_arm,109,618,1,1\n" +
            "Equipment Down|Shirts|sprite_down_L_upper_arm,109,618,1,1\n" +
            "Equipment Down|Shirts|sprite_down_R_lower_arm,109,618,1,1\n" +
            "Equipment Down|Shirts|sprite_down_R_upper_arm,109,618,1,1\n" +
            "Equipment Down|Shirts|sprite_down_body,109,618,1,1\n" +
            "Equipment Down|Shirts|sprite_down_chest,109,618,1,1\n" +
            "Equipment Down|Shoes|sprite_down_L_foot,109,618,1,1\n" +
            "Equipment Down|Shoes|sprite_down_L_lower_leg,109,618,1,1\n" +
            "Equipment Down|Shoes|sprite_down_R_foot,109,618,1,1\n" +
            "Equipment Down|Shoes|sprite_down_R_lower_leg,109,618,1,1\n" +
            "Equipment Down|Shoulder Pads|sprite_down_L,109,618,1,1\n" +
            "Equipment Down|Shoulder Pads|sprite_down_R,109,618,1,1\n" +
            "Equipment Side|Armours|sprite_side_body,109,618,1,1\n" +
            "Equipment Side|Armours|sprite_side_chest,109,618,1,1\n" +
            "Equipment Side|Belts|sprite_side,109,618,1,1\n" +
            "Equipment Side|Gloves|sprite_side_L_hand,109,618,1,1\n" +
            "Equipment Side|Gloves|sprite_side_R_hand,109,618,1,1\n" +
            "Equipment Side|Pants|sprite_side_L_lower_leg,109,618,1,1\n" +
            "Equipment Side|Pants|sprite_side_L_upper_leg,109,618,1,1\n" +
            "Equipment Side|Pants|sprite_side_R_lower_leg,109,618,1,1\n" +
            "Equipment Side|Pants|sprite_side_R_upper_leg,109,618,1,1\n" +
            "Equipment Side|Pants|sprite_side_hip,109,618,1,1\n" +
            "Equipment Side|Shirts|sprite_side_L_lower_arm,109,618,1,1\n" +
            "Equipment Side|Shirts|sprite_side_L_upper_arm,109,618,1,1\n" +
            "Equipment Side|Shirts|sprite_side_R_lower_arm,109,618,1,1\n" +
            "Equipment Side|Shirts|sprite_side_R_upper_arm,109,618,1,1\n" +
            "Equipment Side|Shirts|sprite_side_body,109,618,1,1\n" +
            "Equipment Side|Shirts|sprite_side_chest,109,618,1,1\n" +
            "Equipment Side|Shoes|sprite_side_L_foot,109,618,1,1\n" +
            "Equipment Side|Shoes|sprite_side_L_lower_leg,109,618,1,1\n" +
            "Equipment Side|Shoes|sprite_side_R_foot,109,618,1,1\n" +
            "Equipment Side|Shoes|sprite_side_R_lower_leg,109,618,1,1\n" +
            "Equipment Side|Shoulder Pads|sprite_side_L,109,618,1,1\n" +
            "Equipment Side|Shoulder Pads|sprite_side_R,109,618,1,1\n" +
            "Equipment Up|Armours|sprite_up_body,109,618,1,1\n" +
            "Equipment Up|Armours|sprite_up_chest,109,618,1,1\n" +
            "Equipment Up|Belts|sprite_up,109,618,1,1\n" +
            "Equipment Up|Gloves|sprite_up_L_hand,109,618,1,1\n" +
            "Equipment Up|Gloves|sprite_up_R_hand,109,618,1,1\n" +
            "Equipment Up|Pants|sprite_up_L_lower_leg,109,618,1,1\n" +
            "Equipment Up|Pants|sprite_up_L_upper_leg,109,618,1,1\n" +
            "Equipment Up|Pants|sprite_up_R_lower_leg,109,618,1,1\n" +
            "Equipment Up|Pants|sprite_up_R_upper_leg,109,618,1,1\n" +
            "Equipment Up|Pants|sprite_up_hip,109,618,1,1\n" +
            "Equipment Up|Shirts|sprite_up_L_lower_arm,109,618,1,1\n" +
            "Equipment Up|Shirts|sprite_up_L_upper_arm,109,618,1,1\n" +
            "Equipment Up|Shirts|sprite_up_R_lower_arm,109,618,1,1\n" +
            "Equipment Up|Shirts|sprite_up_R_upper_arm,109,618,1,1\n" +
            "Equipment Up|Shirts|sprite_up_body,109,618,1,1\n" +
            "Equipment Up|Shirts|sprite_up_chest,109,618,1,1\n" +
            "Equipment Up|Shoes|sprite_up_L_foot,109,618,1,1\n" +
            "Equipment Up|Shoes|sprite_up_L_lower_leg,109,618,1,1\n" +
            "Equipment Up|Shoes|sprite_up_R_foot,109,618,1,1\n" +
            "Equipment Up|Shoes|sprite_up_R_lower_leg,109,618,1,1\n" +
            "Equipment Up|Shoulder Pads|sprite_up_L,109,618,1,1\n" +
            "Equipment Up|Shoulder Pads|sprite_up_R,109,618,1,1\n" +
            "Head Objects|Facial Hair|sprite_down,109,618,1,1\n" +
            "Head Objects|Facial Hair|sprite_side,109,618,1,1\n" +
            "Head Objects|Hair|sprite_down,109,618,1,1\n" +
            "Head Objects|Hair|sprite_side,109,618,1,1\n" +
            "Head Objects|Hair|sprite_up,109,618,1,1\n" +
            "Head Objects|Headwear|sprite_down,109,618,1,1\n" +
            "Head Objects|Headwear|sprite_side,109,618,1,1\n" +
            "Head Objects|Headwear|sprite_up,109,618,1,1\n" +
            "Weapon Objects|swing_sprite,109,618,1,1\n" +
            "Weapon Objects|thrust_sprite,109,618,1,1\n" +
            "Weapon Objects|swing_axe_1,112,460,39,105\n" +
            "Weapon Objects|swing_axe_2,97,784,60,109\n" +
            "Weapon Objects|swing_effect,2,2,168,299\n" +
            "Weapon Objects|swing_mace_1,1981,82,33,103\n" +
            "Weapon Objects|swing_mace_2,265,320,58,117\n" +
            "Weapon Objects|swing_sword_1,153,446,54,101\n" +
            "Weapon Objects|swing_sword_2,172,216,46,104\n" +
            "Weapon Objects|thrust_effect,41,460,69,156\n" +
            "Weapon Objects|thrust_spear_1,2,460,37,211\n" +
            "Weapon Objects|thrust_spear_2,172,2,46,212\n" +
            "Body_Down_down_eyebrows_2,1027,957,54,13\n" +
            "Body_Down_down_eyebrows_2_tintable,1027,934,54,12\n" +
            "Body_Down_down_eyebrows_3,1027,916,54,12\n" +
            "Body_Down_down_eyebrows_3_tintable,1027,896,54,12\n" +
            "Body_Side_side_eyebrows_3,1018,777,39,30\n" +
            "Body_Side_side_eyebrows_3_tintable,1018,741,39,30\n" +
            "Body_Side_side_eyebrows_2,1018,706,39,30\n" +
            "Body_Side_side_eyebrows_2_tintable,1018,669,39,30\n" +
            "BodySideside_eyes_2,1025,823,17,18\n" +
            "BodySideside_eyes_3,1025,846,17,18\n" +
            "BodyDowndown_eyes_2,1027,1001,51,20\n" +
            "BodyDowndown_eyes_3,1028,979,51,20\n" +
            "Head ObjectsHairhair_8_up_tintable,1080,449,93,77\n" +
            "Head ObjectsHairhair_8_up,1180,449,93,77\n" +
            "Head ObjectsHairhair_8_side_tintable,1286,475,86,66\n" +
            "Head ObjectsHairhair_8_side,1385,475,86,66\n" +
            "Head ObjectsHairhair_8_down_tintable,1492,461,90,62\n" +
            "Head ObjectsHairhair_8_down,1592,461,90,62\n" +
            "Head ObjectsHairhair_10_side_tintable,1553,603,89,50\n" +
            "Head ObjectsHairhair_9_side_tintable,1553,532,96,69\n" +
            "Head ObjectsHairhair_9_down_tintable,1654,528,82,66\n" +
            "Head ObjectsHairhair_10_down_tintable,1644,596,41,64\n" +
            "Head ObjectsHairhair_9_up_tintable,1687,594,86,96\n" +
            "Head ObjectsHairhair_10_up_tintable,1793,544,44,74\n" +
            "Head ObjectsHairhair_10_up,1746,509,44,74\n" +
            "Head ObjectsHairhair_9_up,1697,414,86,96\n" +
            "Head ObjectsHairhair_10_side,1787,492,89,50\n" +
            "Head ObjectsHairhair_10_down,1878,485,41,64\n" +
            "Head ObjectsHairhair_9_side,1787,421,96,69\n" +
            "Head ObjectsHairhair_9_down,1888,417,82,66";

    public static void chopchop(){
        Pixmap allTexture = new Pixmap(Gdx.files.internal(AssetsConstants.TO_CHOP3));

        //
//        section.drawPixmap(allTexture,0, 0, 64*(i%16), 64*(i/16), 64, 64);
//        Texture cutTexture = new Texture(section);
//        FileHandle outputfile = new FileHandle("aaaItems/" + namesItems.get(i) + jpg);
//        PixmapIO.writePNG(outputfile,cutTexture.getTextureData().consumePixmap());
        //

        String[] lines = blabal.split("\n");
        int stala = allTexture.getHeight();
        Arrays.stream(lines).forEach(l -> {
            final String[] split = l.split(",");
            split[0] = split[0].replaceAll("\\|","_");
            Pixmap section = new Pixmap(Integer.parseInt(split[3]), Integer.parseInt(split[4]),
                    Pixmap.Format.RGBA8888);
            section.drawPixmap(allTexture,0, 0, Integer.parseInt(split[1]), stala - Integer.parseInt(split[2]) - Integer.parseInt(split[4]),
                    Integer.parseInt(split[3]), Integer.parseInt(split[4]));
            Texture cutTexture = new Texture(section);
            FileHandle outputfile = new FileHandle("toChopchop/" + split[0] + ".png");
            PixmapIO.writePNG(outputfile,cutTexture.getTextureData().consumePixmap());
//            System.out.println(Arrays.toString(split));
        });
    }

    private AssetChopper() {
    }

    public static TextureRegion[] crop(Texture texture) {
        TextureRegion textureRegion = new TextureRegion(texture);
        final TextureRegion[][] split = textureRegion.split(SIZE, SIZE);
        return Arrays.stream(split).flatMap(Arrays::stream).toArray(TextureRegion[]::new);
    }

    public static Texture[] crop2(Texture texture) {
        TextureRegion textureRegion = new TextureRegion(texture);
        final TextureRegion[][] split = textureRegion.split(SIZE, SIZE);
        return Arrays.stream(split).flatMap(Arrays::stream).toArray(Texture[]::new);
    }


    public static void saveImages(TextureRegion[] textures) {
        String jpg = ".png";
        String variableColor = "";
        List<String> names = Arrays.asList(
                "dirtUL","dirtU1","dirtU2","dirtU3","dirtUR","dirtDRm"     ,"dirtDLm"     ,"darkGrassUL","darkGrassU","darkGrassUR","waterDirtUL","waterDirtU1","waterDirtU2","waterDirtUR","waterDirtDRm","waterDirtDLm",
                "dirtL1","grass1","grass2","grass3","dirtR1","dirtURm"     ,"dirtULm"     ,"darkGrassL" ,"darkGrass" ,"darkGrassR" ,"waterDirtL1","waterDirt1" ,"waterDirt2" ,"waterDirtR1","waterDirtURm","waterDirtULm",
                "dirtL2","xxx1"  ,"xxx2"  ,"xxx3"  ,"dirtR2","darkGrassDRm","darkGrassDLm","darkGrassDL","darkGrassD","darkGrassDR","waterDirtL2","waterDirt3","waterDirt4","waterDirtR2","fence","fenceI",
                "dirtL3","dirt1" ,"dirt2" ,"dirt3" ,"dirtR3","darkGrassURm","darkGrassULm","brhor1","brhor2","brhor3","waterDirtDL","waterDirtD1","waterDirtD2","waterDirtDR","deska","fenceI2",
                "dirtDL","dirtD1","dirtD2","dirtD3","dirtDR","darkGrass1"  ,"darkGrass2","brhor4","brhor5","brhor6","brver1","brver2","palagU","fenceDR","blank","fenceDL",
                "stoneULX","stoneUXL","stoneUX1","stoneUX2","stoneUXR","stoneURX"                        ,"stairsUL","stairsU","stairsUR","tab1","brver3","brver4","palagM","fenceUD","rock","fenceUD2",
                "stoneLXU","stoneDRm","stoneD1","stoneD2","stoneDLm","stoneRXU"      ,"stairsDL","stairsD","stairsDR","tab2","brver5","brver6","palagD","fenceUR","fenceLR","fenceUL",
                "stoneLX1","stoneR1","envi1","envi2",      "stoneL1","stoneRX1"      ,"stoneULQ","stoneUQL","stoneUQR","stoneURQ","waterGrassUL","waterGrassU1","waterGrassU2","waterGrassUR","waterGrassDRm","waterGrassDLm",
                "stoneLX2","stoneR2","envi3","envi4",       "stoneL2","stoneRX2"      ,"stoneLQU","stoneDRmQ","stoneDLmQ","stoneRQU"          ,"waterGrassL1","waterGrass1","waterGrass2","waterGrassR1","waterGrassURm","waterGrassULm",
                "stoneLXD","stoneURm","stoneU1","stoneU2","stoneULm","stoneRXD"      ,"stoneLQD","stoneURmQ","stoneULmQ","stoneRQD"          ,"waterGrassL2","waterGrass3","waterGrass4","waterGrassR2","doorsL1","doorsL2",
                "stoneDLX","stoneDXL","stoneDX1","stoneDX2","stoneDXR","stoneDRX"      ,"stoneDLQ","stoneDQL","stoneDQR","stoneDRQ","waterGrassDL","waterGrassD1","waterGrassD2","waterGrassDR","doorsL3","doorsL4",
                "tr1","tr2","tr3","tr4","tr5","tr6","tr7","blank2","blank3","blank4","waterFall1","waterFall2","waterFall3","waterFall4","doorsL5","doorsL6",
                "tr8" ,"tr9" ,"tr10","tr11","tr12","tr13","tr14","tr15","tr16","tr17","waterFall5","waterFall6","waterFall7","waterFall8","doorsR1","doorsR2",
                "tr18","tr19","tr20","tr21","tr22","tr23","tr24","tr25","tr26","tr27","waterFall8","waterFall9","waterFall10","waterFall11","doorsR3","doorsR4",
                "tr28","tr29","tr30","tr31","tr32","tr33","tr34","tr35","doorsD1","doorsD2","doorsD3","doorsU1","doorsU2","doorsU3","doorsR5","doorsR6",
                "tr36","tr37","tr38","tr39","tr40","tr41","tr42","tr43","doorsD4","doorsD5","doorsD6","doorsU4","doorsU5","doorsU6","blank5","blank6");
        List<String> namesItems = Arrays.asList(
                "ironDagger","ironShortSword","ironAxe","ironMace","ironLongSword","ironGreatAxe","ironGreatHammer","shortBow","ironArrow","leatherQuiver","ironHelmet","ironArmor","ironGloves","ironBoots","ironShield","ironShieldBack",
                "steelDagger","steelShortSword","steelAxe","steelMace","steelLongSword","steelGreatAxe","steelGreatHammer","compositeBow","steelArrow","steelQuiver","steelHelmet","steelArmor","steelGloves","steelBoots","steelShield","steelShieldBack",
                "goldenDagger","goldenShortSword","goldenAxe","goldenMace","goldenLongSword","goldenGreatAxe","goldenGreatHammer","goldenBow","goldenArrow","goldenQuiver","goldenHelmet","goldenArmor","goldenGloves","goldenBoots","goldenShield","goldenShieldBack",
                "brassDagger","brassShortSword","brassAxe","brassMace","brassLongSword","brassGreatAxe","brassGreatHammer","brassBow","brassArrow","brassQuiver","brassHelmet","brassArmor","brassGloves","brassBoots","brassShield","brassShieldBack",
                "elvenDagger","elvenShortSword","elvenAxe","elvenMace","elvenLongSword","elvenGreatAxe","elvenGreatHammer","elvenBow","elvenArrow","elvenQuiver","elvenHelmet","elvenArmor","elvenGloves","elvenBoots","elvenShield","elvenShieldBack",
                "moonstoneDagger","moonstoneShortSword","moonstoneAxe","moonstoneMace","moonstoneLongSword","moonstoneGreatAxe","moonstoneGreatHammer","moonstoneBow","moonstoneArrow","moonstoneQuiver","moonstoneHelmet","moonstoneArmor","moonstoneGloves","moonstoneBoots","moonstoneShield","moonstoneShieldBack",
                "obsidianDagger","obsidianShortSword","obsidianAxe","obsidianMace","obsidianLongSword","obsidianGreatAxe","obsidianGreatHammer","obsidianBow","obsidianArrow","obsidianQuiver","obsidianHelmet","obsidianArmor","obsidianGloves","obsidianBoots","obsidianShield","obsidianShieldBack",
                "dragonDagger","dragonShortSword","dragonAxe","dragonMace","dragonLongSword","dragonGreatAxe","dragonGreatHammer","dragonBow","dragonArrow","dragonQuiver","dragonHelmet","dragonArmor","dragonGloves","dragonBoots","dragonShield","dragonShieldBack",
                "knife","smallHammer","sickle","rapier","sabre","ogreSword","ogreAxe","ogreBow","ogreArrow","ogreQuiver","silverHelmet","silverArmor","silverGloves","silverBoots","adeptArmor","clothArmor",
                "pickaxe","morgenstern","flail","crystalAxe","crystalDagger","crystalSword","crystalGreatHammer","crystalAxeBow","crystalArrow","crystalQuiver","hunterHelmet","hunterArmor","hunterGloves","hunterBoots","guardArmor","knightArmor",
                "blowgun","slingshot","whip","club","greatClub","smallCrossbow","crossbow","battleCrossbow","belt","beltQuiver","hardLeatherHelmet","hardLeatherArmor","hardLeatherGloves","hardLeatherBoots","militiaArmor","chainmailArmor",
                "staff","battleStaff","pike","spear","trident","glaive","halabard","broadSword","gladius","longBow","leatherHelmet","leatherArmor","leatherGloves","leatherBoots","ritualMask","hood",
                "clanShield1","clanShield2","clanShield3","clanShield4","clanShield5","clanShield6","clanShield7","clanShield8","clanShield9","chainmailHelmet","furHelmet","furArmor","furGloves","furBoots","eliteHelmet","eliteArmor",
                "backpack1","backpack2","pouch1","pouch2","purse","bag","chest","barrel","bucket","measure1","measure2","rope1","rope2","hook","nails","wtf",
                "rockFish","rockFishChest","candle1","candle2","candle3","torch","candle4","lamp1","lamp2","loupe","cup1","cup2","cup3","cup4","dishes","pot",
                "potionFlask1","potionFlask2","potionFlask3","potionFlask4","potionFlask5","potionFlask6","potionFlask7","potionFlask8","potionFlask9","mirror","trap","stick","powder","spikes","whistle","lens",
                "fishingRod","roll1","roll2","roll3","potionFlask10","ropeHook","package","merchantBox","dunno","crowbar","hammer","axe","hammer2","fishingTools","shovel","pickaxe2",
                "horseAdd1","horseAdd2","horseAdd3","horseAdd4","horseAdd5","travel1","travel2","travel3","travel4","travel5","net","joints","wood","stone","bricks","glass",
                "key1","key2","key3","key4","keyRing","pickLock","pickLockSet","lock1","lock2","lock3","handcuffs","druidSuit","citizenSuit1","citizenSuit2","citizenSuit3","clownSuit",
                "bell","wtf2","wtf3","wtf4","chain","libra","hourglass","ladder","hiddenSword","chess","jewelry","cape","robe","goldenRoyalSword","royalSword","branch",
                "ametist","meteorite","diamond","emerald","amber","sapphire","onyx","pearl","pearl2","jade","mineral","ruby","amethyst","dragonStone","citrin","aquamarine",
                "crown","royalCrown","goldenStatue","pedant","wtf5","horn","ring1","elephant","picture","ring2","wtf6","soap","cape2","wtf7","cape3","brokenBlade",
                "wtf0","ring3","feather","paper1","book1","book2","book3","book4","paper2","paper3","scroll1","scroll2","scroll3","listUnknown","listHero","listLove",
                "towels1","towels2","towels3","towels4","towels5","oats","herbs","tea","rice","potato","coffee","salt","flour","spices","sugar","beer",
                "copperOre","coalOre","ironOre","goldOre","silverOre","copperBar","bar1","bar2","steelBar","ironBar","brassBar","goldBar","silverBar","corn","wheat","cheese",
                "chicken","goat","cow","pig","sheep","dog","donkey","horse1","horse2","horse3","horse4","horse5","fish","rat","reaven","cat"
        );
        namesItems.forEach(name -> name = name.replace(" ",""));
        for (int i = 0; i < 416; i++) {
            Pixmap allTexture = new Pixmap(Gdx.files.internal(AssetsConstants.TO_CHOP2));
            Pixmap section = new Pixmap(64, 64, Pixmap.Format.RGBA8888);
            section.drawPixmap(allTexture,0, 0, 64*(i%16), 64*(i/16), 64, 64);
            Texture cutTexture = new Texture(section);
            FileHandle outputfile = new FileHandle("aaaItems/" + namesItems.get(i) + jpg);
            PixmapIO.writePNG(outputfile,cutTexture.getTextureData().consumePixmap());
        }
    }



}
