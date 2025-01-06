INSERT INTO CHASSIS (NAME, MANUFACTURER, SIDE_PANEL, MOBO_FORM_FACTOR, PSU_FORM_FACTOR, COLOR, PRICE)
VALUES ('Cooler Master HAF 700', 'Cooler Master', TRUE, 'E-ATX', 'ATX', 'Black', 250),
       ('Be Quiet! Pure Base 500DX', 'Be Quiet!', TRUE, 'ATX', 'ATX', 'White', 100),
       ('Thermaltake View 71', 'Thermaltake', TRUE, 'E-ATX', 'ATX', 'Black', 170),
       ('Lian Li PC-O11 Dynamic', 'Lian Li', TRUE, 'ATX', 'ATX', 'Black', 140),
       ('Corsair 5000D', 'Corsair', TRUE, 'ATX', 'ATX', 'White', 160),
       ('NZXT H7 Flow', 'NZXT', TRUE, 'ATX', 'ATX', 'Black', 130),
       ('Fractal Design Define 7', 'Fractal Design', TRUE, 'ATX', 'ATX', 'Black', 180),
       ('Phanteks Enthoo Pro', 'Phanteks', TRUE, 'ATX', 'ATX', 'White', 110),
       ('Antec P120 Crystal', 'Antec', TRUE, 'ATX', 'ATX', 'Black', 130),
       ('Thermaltake Core P3', 'Thermaltake', TRUE, 'E-ATX', 'ATX', 'Black', 150),
       ('Lian Li Lancool 215', 'Lian Li', TRUE, 'ATX', 'ATX', 'White', 90),
       ('Corsair 7000D', 'Corsair', TRUE, 'E-ATX', 'ATX', 'Black', 280),
       ('Cooler Master TD500 Mesh', 'Cooler Master', TRUE, 'ATX', 'ATX', 'White', 100),
       ('NZXT H510 Elite', 'NZXT', TRUE, 'ATX', 'ATX', 'Black', 150),
       ('Fractal Design Pop XL', 'Fractal Design', TRUE, 'E-ATX', 'ATX', 'Black', 120);
INSERT INTO COOLER (NAME, MANUFACTURER, WATER_COOLING, FAN_SIZE, RADIATOR_SIZE, RGB, SOCKET_TYPE, COLOR, PRICE)
VALUES ('Arctic Liquid Freezer II 280', 'Arctic', TRUE, 140, 280, FALSE, 'LGA1700', 'Black', 120),
       ('Noctua NH-U12S', 'Noctua', FALSE, 120, NULL, FALSE, 'AM5', 'Beige', 70),
       ('be quiet! Dark Rock Pro 4', 'be quiet!', FALSE, 135, NULL, FALSE, 'AM4', 'Black', 90),
       ('Corsair iCUE H150i', 'Corsair', TRUE, 120, 360, TRUE, 'LGA1700', 'Black', 200),
       ('NZXT Kraken Z73', 'NZXT', TRUE, 120, 360, TRUE, 'LGA1200', 'Black', 250),
       ('Cooler Master MasterLiquid ML240L', 'Cooler Master', TRUE, 120, 240, TRUE, 'AM4', 'Black', 80),
       ('DeepCool AK620', 'DeepCool', FALSE, 120, NULL, FALSE, 'AM5', 'Black', 65),
       ('Thermaltake Floe DX RGB 280', 'Thermaltake', TRUE, 140, 280, TRUE, 'AM4', 'White', 180),
       ('Noctua NH-L12S', 'Noctua', FALSE, 120, NULL, FALSE, 'AM4', 'Beige', 60),
       ('EK-AIO 360 D-RGB', 'EKWB', TRUE, 120, 360, TRUE, 'LGA1200', 'Black', 230),
       ('be quiet! Shadow Rock 3', 'be quiet!', FALSE, 120, NULL, FALSE, 'AM5', 'Silver', 50),
       ('Corsair H115i Elite', 'Corsair', TRUE, 140, 280, TRUE, 'LGA1700', 'White', 190),
       ('Thermaltake Water 3.0', 'Thermaltake', TRUE, 120, 240, TRUE, 'LGA1151', 'Black', 130),
       ('NZXT T120', 'NZXT', FALSE, 120, NULL, FALSE, 'AM4', 'White', 40),
       ('DeepCool LT720', 'DeepCool', TRUE, 120, 360, TRUE, 'LGA1700', 'Black', 160);

INSERT INTO CPU (NAME, MANUFACTURER, ARCHITECTURE, WATTAGE_USAGE, SOCKET_TYPE, CPU_MODEL, CORE_COUNT, CLOCK_SPEED,
                 PRICE)
VALUES ('Intel Core i5-12600K', 'Intel', 'Alder Lake', 125, 'LGA1700', 'i5-12600K', 10, 3.7, 320),
       ('AMD Ryzen 7 5800X', 'AMD', 'Zen 3', 105, 'AM4', 'Ryzen 7 5800X', 8, 3.8, 400),
       ('Intel Core i3-12100', 'Intel', 'Alder Lake', 60, 'LGA1700', 'i3-12100', 4, 3.3, 150),
       ('AMD Ryzen 5 3600', 'AMD', 'Zen 2', 65, 'AM4', 'Ryzen 5 3600', 6, 3.6, 200),
       ('Intel Core i9-13900K', 'Intel', 'Raptor Lake', 125, 'LGA1700', 'i9-13900K', 24, 3.0, 650),
       ('AMD Ryzen 9 7950X', 'AMD', 'Zen 4', 170, 'AM5', 'Ryzen 9 7950X', 16, 4.5, 700),
       ('Intel Core i7-13700K', 'Intel', 'Raptor Lake', 125, 'LGA1700', 'i7-13700K', 16, 3.4, 450),
       ('AMD Ryzen 7 7700X', 'AMD', 'Zen 4', 105, 'AM5', 'Ryzen 7 7700X', 8, 4.0, 400),
       ('Intel Core i5-13600K', 'Intel', 'Raptor Lake', 125, 'LGA1700', 'i5-13600K', 14, 3.5, 350),
       ('AMD Ryzen 5 7600', 'AMD', 'Zen 4', 65, 'AM5', 'Ryzen 5 7600', 6, 3.8, 250),
       ('Intel Core i3-13100F', 'Intel', 'Raptor Lake', 60, 'LGA1700', 'i3-13100F', 4, 3.4, 120),
       ('AMD Ryzen Threadripper 3960X', 'AMD', 'Zen 2', 280, 'sTRX4', 'Threadripper 3960X', 24, 3.8, 1400),
       ('Intel Core i9-10900KF', 'Intel', 'Comet Lake', 125, 'LGA1200', 'i9-10900KF', 10, 3.7, 450),
       ('AMD Ryzen 3 3100', 'AMD', 'Zen 2', 65, 'AM4', 'Ryzen 3 3100', 4, 3.6, 100),
       ('Intel Pentium Gold G6405', 'Intel', 'Comet Lake', 58, 'LGA1200', 'G6405', 2, 4.1, 65);

INSERT INTO DATA (NAME, MANUFACTURER, DATA_MODEL, INTERFACE_TYPE, STORAGE_TYPE, WATTAGE_USAGE, CAPACITY, CACHE_MEMORY,
                  PRICE)
VALUES ('Samsung 980 Pro', 'Samsung', 'MZ-V8P1T0BW', 'NVMe', 'SSD', 6, 1000, 1024, 180),
       ('Seagate FireCuda 530', 'Seagate', 'ZP1000GM3A013', 'NVMe', 'SSD', 7, 1000, 1024, 190),
       ('Western Digital Black SN850X', 'Western Digital', 'WDS100T2X0E', 'NVMe', 'SSD', 6, 1000, 1024, 200),
       ('Crucial P3 Plus', 'Crucial', 'CT1000P3PSSD8', 'NVMe', 'SSD', 5, 1000, 512, 120),
       ('Kingston NV2', 'Kingston', 'SNV2S/1000G', 'NVMe', 'SSD', 5, 1000, 256, 70),
       ('SanDisk Ultra 3D', 'SanDisk', 'SDSSDH3-1T00-G25', 'SATA', 'SSD', 4, 1000, 256, 100),
       ('Toshiba X300', 'Toshiba', 'HDWR11AXZSTA', 'SATA', 'HDD', 9, 8000, 256, 220),
       ('Western Digital Red Plus', 'Western Digital', 'WD40EFRX', 'SATA', 'HDD', 5, 4000, 128, 140),
       ('Seagate IronWolf Pro', 'Seagate', 'ST6000NE000', 'SATA', 'HDD', 7, 6000, 256, 180),
       ('Samsung T7 Portable', 'Samsung', 'MU-PC1T0T/AM', 'USB', 'SSD', 5, 1000, NULL, 150),
       ('WD My Passport', 'Western Digital', 'WDBPKJ0040BBK', 'USB', 'HDD', 3, 4000, NULL, 100),
       ('Corsair MP600 PRO XT', 'Corsair', 'CSSD-F1TBMP600PXT', 'NVMe', 'SSD', 7, 1000, 1024, 190),
       ('Crucial BX500', 'Crucial', 'CT960BX500SSD1', 'SATA', 'SSD', 3, 960, 128, 50),
       ('Toshiba Canvio Basics', 'Toshiba', 'HDTB410XK3AA', 'USB', 'HDD', 4, 1000, NULL, 50),
       ('Kingston KC3000', 'Kingston', 'SKC3000S/1024G', 'NVMe', 'SSD', 6, 1000, 1024, 170);

INSERT INTO GPU (NAME, MANUFACTURER, WATTAGE_USAGE, GPU_MODEL, CHIPSET, MEMORY_CAPACITY, INTERFACE_TYPE, CLOCK_SPEED,
                 COLOR, PRICE)
VALUES ('NVIDIA GeForce RTX 4090', 'NVIDIA', 450, 'RTX 4090', 'Ada Lovelace', 24, 'PCIe 4.0', '2.23 GHz', 'Black',
        1600),
       ('AMD Radeon RX 7900 XTX', 'AMD', 355, 'RX 7900 XTX', 'RDNA 3', 24, 'PCIe 4.0', '2.3 GHz', 'Red', 1000),
       ('NVIDIA GeForce RTX 4060', 'NVIDIA', 110, 'RTX 4060', 'Ada Lovelace', 8, 'PCIe 4.0', '2.31 GHz', 'Black', 300),
       ('AMD Radeon RX 7600', 'AMD', 165, 'RX 7600', 'RDNA 3', 8, 'PCIe 4.0', '2.6 GHz', 'Red', 270),
       ('NVIDIA GeForce GTX 1660 Super', 'NVIDIA', 125, 'GTX 1660 Super', 'Turing', 6, 'PCIe 3.0', '1.78 GHz', 'Black',
        250),
       ('AMD Radeon RX 550', 'AMD', 50, 'RX 550', 'GCN 4', 4, 'PCIe 3.0', '1.18 GHz', 'Red', 100),
       ('NVIDIA GeForce RTX 2080 Ti', 'NVIDIA', 250, 'RTX 2080 Ti', 'Turing', 11, 'PCIe 3.0', '1.63 GHz', 'Black', 900),
       ('AMD Radeon RX Vega 64', 'AMD', 295, 'RX Vega 64', 'Vega', 8, 'PCIe 3.0', '1.56 GHz', 'Silver', 400),
       ('NVIDIA Quadro RTX 5000', 'NVIDIA', 265, 'RTX 5000', 'Turing', 16, 'PCIe 3.0', '1.62 GHz', 'Black', 2000),
       ('Intel Arc A770', 'Intel', 225, 'A770', 'Alchemist', 16, 'PCIe 4.0', '2.1 GHz', 'Black', 350),
       ('NVIDIA GeForce RTX 3050', 'NVIDIA', 130, 'RTX 3050', 'Ampere', 8, 'PCIe 4.0', '1.78 GHz', 'Black', 300),
       ('AMD Radeon RX 6800', 'AMD', 250, 'RX 6800', 'RDNA 2', 16, 'PCIe 4.0', '1.81 GHz', 'Red', 580),
       ('NVIDIA GeForce GTX 1050 Ti', 'NVIDIA', 75, 'GTX 1050 Ti', 'Pascal', 4, 'PCIe 3.0', '1.29 GHz', 'Black', 150),
       ('AMD Radeon RX 580', 'AMD', 185, 'RX 580', 'Polaris', 8, 'PCIe 3.0', '1.34 GHz', 'Black', 180),
       ('NVIDIA GeForce RTX 4070 Ti', 'NVIDIA', 285, 'RTX 4070 Ti', 'Ada Lovelace', 12, 'PCIe 4.0', '2.61 GHz', 'Black',
        800);
INSERT INTO MOBO (NAME, MANUFACTURER, CHIPSET, WATTAGE_USAGE, MOBO_FORM_FACTOR, MEMORY_TYPE, INTERFACE_TYPE,
                  SOCKET_TYPE, PRICE, M2SUPPORT)
VALUES ('ASUS TUF Gaming B550-PLUS', 'ASUS', 'B550', 65, 'ATX', 'DDR4', 'PCIe 4.0', 'AM4', 160, 'Yes'),
       ('MSI MPG Z790 EDGE', 'MSI', 'Z790', 75, 'ATX', 'DDR5', 'PCIe 5.0', 'LGA1700', 450, 'Yes'),
       ('Gigabyte B450M DS3H', 'Gigabyte', 'B450', 55, 'Micro-ATX', 'DDR4', 'PCIe 3.0', 'AM4', 90, 'No'),
       ('ASRock Z590 Steel Legend', 'ASRock', 'Z590', 70, 'ATX', 'DDR4', 'PCIe 4.0', 'LGA1200', 200, 'Yes'),
       ('ASUS ROG Crosshair VIII Hero', 'ASUS', 'X570', 80, 'ATX', 'DDR4', 'PCIe 4.0', 'AM4', 380, 'Yes'),
       ('MSI MAG B760M Mortar', 'MSI', 'B760', 65, 'Micro-ATX', 'DDR5', 'PCIe 5.0', 'LGA1700', 200, 'Yes'),
       ('Gigabyte Z790 AORUS Elite AX', 'Gigabyte', 'Z790', 70, 'ATX', 'DDR5', 'PCIe 5.0', 'LGA1700', 290, 'Yes'),
       ('ASRock H510M-ITX/ac', 'ASRock', 'H510', 50, 'Mini-ITX', 'DDR4', 'PCIe 3.0', 'LGA1200', 120, 'No'),
       ('MSI MPG X670E Carbon WiFi', 'MSI', 'X670E', 85, 'ATX', 'DDR5', 'PCIe 5.0', 'AM5', 500, 'Yes'),
       ('Gigabyte B650M AORUS Elite AX', 'Gigabyte', 'B650M', 60, 'Micro-ATX', 'DDR5', 'PCIe 5.0', 'AM5', 220, 'Yes'),
       ('ASUS Prime Z690-P', 'ASUS', 'Z690', 65, 'ATX', 'DDR5', 'PCIe 4.0', 'LGA1700', 250, 'Yes'),
       ('ASRock B550 Phantom Gaming', 'ASRock', 'B550', 60, 'ATX', 'DDR4', 'PCIe 4.0', 'AM4', 150, 'Yes'),
       ('MSI B450 GAMING PRO CARBON AC', 'MSI', 'B450', 55, 'ATX', 'DDR4', 'PCIe 3.0', 'AM4', 140, 'No'),
       ('Gigabyte Z590 AORUS Master', 'Gigabyte', 'Z590', 75, 'ATX', 'DDR4', 'PCIe 4.0', 'LGA1200', 360, 'Yes'),
       ('ASUS ROG Maximus XIII Hero', 'ASUS', 'Z590', 85, 'ATX', 'DDR4', 'PCIe 4.0', 'LGA1200', 500, 'Yes');

INSERT INTO PSU (NAME, MANUFACTURER, WATTAGE_CAPACITY, PSU_MODEL, EFFICIENCY, PSU_FORM_FACTOR, COLOR, PRICE)
VALUES ('EVGA SuperNOVA 1000 G6', 'EVGA', 1000, '1000 G6', '80+ Gold', 'ATX', 'Black', 200),
       ('Corsair HX1200', 'Corsair', 1200, 'HX1200', '80+ Platinum', 'ATX', 'Black', 300),
       ('Seasonic PRIME TX-850', 'Seasonic', 850, 'TX-850', '80+ Titanium', 'ATX', 'Black', 250),
       ('Cooler Master MWE Gold V2 750', 'Cooler Master', 750, 'MWE Gold V2', '80+ Gold', 'ATX', 'Black', 120),
       ('Thermaltake Toughpower GF1 850W', 'Thermaltake', 850, 'GF1 850W', '80+ Gold', 'ATX', 'Black', 160),
       ('SilverStone SX1000', 'SilverStone', 1000, 'SX1000', '80+ Platinum', 'SFX', 'Black', 300),
       ('NZXT C750', 'NZXT', 750, 'C750', '80+ Bronze', 'ATX', 'White', 130),
       ('Corsair SF600', 'Corsair', 600, 'SF600', '80+ Platinum', 'SFX', 'Black', 140),
       ('Antec High Current Gamer 850W', 'Antec', 850, 'HCG850', '80+ Gold', 'ATX', 'Black', 150),
       ('FSP Hydro PTM Pro 1200W', 'FSP', 1200, 'Hydro PTM Pro', '80+ Platinum', 'ATX', 'Black', 300),
       ('Thermaltake Smart RGB 700W', 'Thermaltake', 700, 'Smart RGB 700W', '80+ White', 'ATX', 'Black', 60),
       ('Be Quiet! Dark Power Pro 12 1500W', 'Be Quiet!', 1500, 'Dark Power Pro 12', '80+ Titanium', 'ATX', 'Black',
        450),
       ('EVGA 600 W1', 'EVGA', 600, 'W1', '80+ White', 'ATX', 'Black', 40),
       ('Corsair CX650M', 'Corsair', 650, 'CX650M', '80+ Bronze', 'ATX', 'Black', 90),
       ('Seasonic Focus PX-750', 'Seasonic', 750, 'PX-750', '80+ Platinum', 'ATX', 'Black', 160);

INSERT INTO RAM (NAME, MANUFACTURER, WATTAGE_USAGE, MEMORY_CAPACITY, MEMORY_TYPE, TIMINGS, CLOCK_SPEED, PRICE)
VALUES ('Corsair Dominator Platinum', 'Corsair', 12, 32, 'DDR4', 16, 3600, 200),
       ('Kingston Fury Beast', 'Kingston', 10, 16, 'DDR4', 16, 3200, 80),
       ('Patriot Viper Steel', 'Patriot', 9, 16, 'DDR4', 16, 3600, 90),
       ('G.SKILL Ripjaws V', 'G.SKILL', 9, 8, 'DDR4', 18, 3200, 50),
       ('Corsair Vengeance RGB Pro SL', 'Corsair', 10, 32, 'DDR4', 16, 3200, 160),
       ('Team T-Force Delta RGB', 'Team', 10, 16, 'DDR4', 16, 3200, 90),
       ('Crucial RAM 8GB DDR4', 'Crucial', 5, 8, 'DDR4', 22, 2400, 40),
       ('ADATA XPG SPECTRIX D60G', 'ADATA', 10, 16, 'DDR4', 16, 3000, 85),
       ('HyperX Predator', 'Kingston', 11, 16, 'DDR4', 15, 3600, 120),
       ('G.SKILL Aegis', 'G.SKILL', 9, 16, 'DDR4', 18, 3000, 60),
       ('Corsair ValueSelect', 'Corsair', 5, 8, 'DDR4', 20, 2400, 35),
       ('Team T-Create Classic 10L', 'Team', 9, 16, 'DDR4', 16, 2666, 60),
       ('Kingston Fury Renegade RGB', 'Kingston', 10, 32, 'DDR4', 16, 3600, 170),
       ('Patriot Signature Line', 'Patriot', 5, 8, 'DDR4', 19, 2666, 40),
       ('Samsung DDR4-2666', 'Samsung', 5, 16, 'DDR4', 19, 2666, 70);

INSERT INTO CHASSIS (NAME, MANUFACTURER, SIDE_PANEL, MOBO_FORM_FACTOR, PSU_FORM_FACTOR, COLOR, PRICE)
VALUES ('O11 Dynamic', 'Lian Li', TRUE, 'ATX', 'ATX', 'Black', 130);

INSERT INTO COOLER (NAME, MANUFACTURER, WATER_COOLING, FAN_SIZE, RADIATOR_SIZE, RGB, SOCKET_TYPE, COLOR, PRICE)
VALUES ('Corsair H150i Elite Capellix LCD', 'Corsair', TRUE, 120, 360, TRUE, 'AM4', 'Black', 180);

INSERT INTO CPU (NAME, MANUFACTURER, ARCHITECTURE, WATTAGE_USAGE, SOCKET_TYPE, CPU_MODEL, CORE_COUNT, CLOCK_SPEED,
                 PRICE)
VALUES ('AMD Ryzen 9 5900X', 'AMD', 'Zen 3', 105, 'AM4', 'Ryzen 9 5900X', 12, 3.7, 550);

INSERT INTO DATA (NAME, MANUFACTURER, DATA_MODEL, INTERFACE_TYPE, STORAGE_TYPE, WATTAGE_USAGE, CAPACITY, CACHE_MEMORY,
                  PRICE)
VALUES ('Samsung 970 Evo Plus', 'Samsung', 'MZ-V7E1T0BW', 'NVMe', 'SSD', 5, 2000, 1024, 300);

INSERT INTO GPU (NAME, MANUFACTURER, WATTAGE_USAGE, GPU_MODEL, CHIPSET, MEMORY_CAPACITY, INTERFACE_TYPE, CLOCK_SPEED,
                 COLOR, PRICE)
VALUES ('MSI Gaming X Trio RTX 4080', 'MSI', 320, 'RTX 4080', 'Ampere', 16, 'PCIe 4.0', '1.44 GHz', 'Black', 1600);

INSERT INTO MOBO (NAME, MANUFACTURER, CHIPSET, WATTAGE_USAGE, MOBO_FORM_FACTOR, MEMORY_TYPE, INTERFACE_TYPE,
                  SOCKET_TYPE, PRICE, M2SUPPORT)
VALUES ('Gigabyte B550 AORUS Master', 'Gigabyte', 'B550', 70, 'ATX', 'DDR4', 'PCIe 4.0', 'AM4', 250, 'Yes');

INSERT INTO PSU (NAME, MANUFACTURER, WATTAGE_CAPACITY, PSU_MODEL, EFFICIENCY, PSU_FORM_FACTOR, COLOR, PRICE)
VALUES ('Corsair RM1000x', 'Corsair', 1000, 'RM1000x', '80+ Gold', 'ATX', 'Black', 130);

INSERT INTO RAM (NAME, MANUFACTURER, WATTAGE_USAGE, MEMORY_CAPACITY, MEMORY_TYPE, TIMINGS, CLOCK_SPEED, PRICE)
VALUES ('Corsair DDR4 Vengeance RGB', 'Corsair', 10, 64, 'DDR4', 16, 3600, 300);



INSERT INTO PCBUILDS (BUILD_NAME, CPU_ID, MOBO_ID, GPU_ID, RAM_ID, CHASSIS_ID, COOLER_ID, STORAGE_ID, PSU_ID,
                      MEMORY_QUANTITIES, STORAGE_QUANTITIES)
VALUES
    ('My PC Build',
     16,
     16,
     16,
     16,
     16,
     16,
     16,
     16,
     4,
     3);


INSERT INTO PCBUILDS (BUILD_NAME, CPU_ID, MOBO_ID, GPU_ID, RAM_ID, CHASSIS_ID, COOLER_ID, STORAGE_ID, PSU_ID,
                      MEMORY_QUANTITIES, STORAGE_QUANTITIES)
VALUES ('Test Build', 2, 2, 1, 2, 1, 1, 4, 1, 2, 1),
       ('High-Performance AMD Setup', 2, 3, 2, 3, 2, 3, 2, 3, 2, 1),
       ('Budget Intel Build', 4, 4, 3, 4, 3, 4, 3, 4, 2, 1),
       ('Mid-Range Gaming PC', 3, 2, 3, 2, 2, 2, 3, 2, 2, 1),
       ('Ultra Budget Build', 4, 4, 4, 3, 4, 4, 4, 5, 1, 1),
       ('High-End AMD Gaming PC', 1, 5, 2, 1, 1, 1, 1, 1, 2, 1),
       ('Intel Workstation Build', 3, 1, 4, 1, 2, 2, 1, 3, 2, 1),
       ('Content Creation Build', 1, 3, 1, 2, 2, 1, 2, 2, 2, 2),
       ('Entry-Level Work PC', 3, 4, 3, 2, 4, 4, 4, 5, 1, 1);

// Mijn eigen PC

