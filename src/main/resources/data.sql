-- Insert into CASE table
INSERT INTO CHASSIS (ID, NAME, MANUFACTURER, SIDE_PANEL, MOBO_FORM_FACTOR, PSU_FORM_FACTOR, COLOR, PRICE)
VALUES (1, 'Corsair 4000D', 'Corsair', TRUE, 'ATX', 'ATX', 'Black', 85),
       (2, 'NZXT H510', 'NZXT', TRUE, 'ATX', 'ATX', 'White', 70),
       (3, 'Fractal Design Meshify C', 'Fractal Design', TRUE, 'ATX', 'ATX', 'Black', 100),
       (4, 'Phanteks Eclipse P400A', 'Phanteks', TRUE, 'ATX', 'ATX', 'White', 90);

-- Insert into COOLING table
INSERT INTO COOLING (ID, NAME, MANUFACTURER, WATER_COOLING, FAN_SIZE, RADIATOR_SIZE, RGB, SOCKET_TYPE, COLOR, PRICE)
VALUES (1, 'Corsair H100i', 'Corsair', TRUE, 120, 240, TRUE, 'LGA1151', 'Black', 150),
       (2, 'Noctua NH-D15', 'Noctua', FALSE, 140, NULL, FALSE, 'AM4', 'Brown', 90),
       (3, 'NZXT Kraken X63', 'NZXT', TRUE, 140, 280, TRUE, 'LGA1200', 'Black', 160),
       (4, 'Cooler Master Hyper 212 EVO', 'Cooler Master', FALSE, 120, NULL, FALSE, 'AM4', 'Black', 35);

-- Insert into CPU table
INSERT INTO CPU (ID, NAME, MANUFACTURER, ARCHITECTURE, WATTAGE_USAGE, SOCKET_TYPE, CPU_MODEL, CORE_COUNT, CLOCK_SPEED,
                 PRICE)
VALUES (1, 'Intel Core i9-12900K', 'Intel', 'Alder Lake', 125, 'LGA1700', 'i9-12900K', 16, 3.2, 600),
       (2, 'AMD Ryzen 9 5900X', 'AMD', 'Zen 3', 105, 'AM4', 'Ryzen 9 5900X', 12, 3.7, 550),
       (3, 'Intel Core i7-12700K', 'Intel', 'Alder Lake', 125, 'LGA1700', 'i7-12700K', 12, 3.6, 420),
       (4, 'AMD Ryzen 5 5600X', 'AMD', 'Zen 3', 65, 'AM4', 'Ryzen 5 5600X', 6, 3.7, 300);

-- Insert into DATA table
INSERT INTO DATA (ID, NAME, MANUFACTURER, DATA_MODEL, INTERFACE_TYPE, STORAGE_TYPE, WATTAGE_USAGE, CAPACITY,
                  CACHE_MEMORY, PRICE)
VALUES (1, 'Samsung 970 Evo', 'Samsung', 'MZ-V7E1T0BW', 'NVMe', 'SSD', 5, 1000, 1024, 150),
       (2, 'Seagate Barracuda', 'Seagate', 'ST1000DM010', 'SATA', 'HDD', 6, 1000, 64, 45),
       (3, 'Western Digital Blue', 'Western Digital', 'WD10EZEX', 'SATA', 'HDD', 6, 1000, 64, 50),
       (4, 'Crucial MX500', 'Crucial', 'CT1000MX500SSD1', 'SATA', 'SSD', 5, 1000, 512, 120);

-- Insert into GPU table
INSERT INTO GPU (ID, NAME, MANUFACTURER, WATTAGE_USAGE, GPU_MODEL, CHIPSET, MEMORY_CAPACITY, INTERFACE_TYPE,
                 CLOCK_SPEED, COLOR, PRICE)
VALUES (1, 'NVIDIA GeForce RTX 3080', 'NVIDIA', 320, 'RTX 3080', 'Ampere', 10, 'PCIe 4.0', '1.44 GHz', 'Black', 700),
       (2, 'AMD Radeon RX 6800 XT', 'AMD', 300, 'RX 6800 XT', 'RDNA 2', 16, 'PCIe 4.0', '1.82 GHz', 'Red', 650),
       (3, 'NVIDIA GeForce RTX 3070', 'NVIDIA', 220, 'RTX 3070', 'Ampere', 8, 'PCIe 4.0', '1.50 GHz', 'Black', 500),
       (4, 'AMD Radeon RX 6700 XT', 'AMD', 230, 'RX 6700 XT', 'RDNA 2', 12, 'PCIe 4.0', '1.94 GHz', 'Red', 480);

-- Insert into MOBO table
INSERT INTO MOBO (ID, NAME, MANUFACTURER, ARCHITECTURE, WATTAGE_USAGE, MOBO_FORM_FACTOR, MEMORY_TYPE, INTERFACE_TYPE,
                  SOCKET_TYPE, PRICE)
VALUES (1, 'ASUS ROG Strix Z690-E', 'ASUS', 'LGA1700', 70, 'ATX', 'DDR5', 'PCIe 5.0', 'LGA1700', 400),
       (2, 'MSI MAG B550 TOMAHAWK', 'MSI', 'AM4', 60, 'ATX', 'DDR4', 'PCIe 4.0', 'AM4', 180),
       (3, 'Gigabyte AORUS X570 Master', 'Gigabyte', 'AM4', 80, 'ATX', 'DDR4', 'PCIe 4.0', 'AM4', 360),
       (4, 'ASRock B450M Pro4', 'ASRock', 'AM4', 50, 'Micro-ATX', 'DDR4', 'PCIe 3.0', 'AM4', 80);

-- Insert into PSU table
INSERT INTO PSU (ID, NAME, MANUFACTURER, WATTAGE_CAPACITY, PSU_MODEL, EFFICIENCY, PSU_FORM_FACTOR, COLOR, PRICE)
VALUES (1, 'Corsair RM750x', 'Corsair', 750, 'RM750x', '80+ Gold', 'ATX', 'Black', 130),
       (2, 'EVGA SuperNOVA 850 G5', 'EVGA', 850, '850 G5', '80+ Gold', 'ATX', 'Black', 140),
       (3, 'Seasonic Focus GX-650', 'Seasonic', 650, 'GX-650', '80+ Gold', 'ATX', 'Black', 110),
       (4, 'Cooler Master V850', 'Cooler Master', 850, 'V850', '80+ Platinum', 'ATX', 'Black', 160);

-- Insert into RAM table
INSERT INTO RAM (ID, NAME, MANUFACTURER, WATTAGE_USAGE, MEMORY_CAPACITY, MEMORY_TYPE, TIMINGS, CLOCK_SPEED, PRICE)
VALUES (1, 'G.SKILL Trident Z RGB', 'G.SKILL', 10, '16GB', 'DDR4', 16, 3200, 100),
       (2, 'Corsair Vengeance LPX', 'Corsair', 9, '8GB', 'DDR4', 18, 3000, 50),
       (3, 'Kingston HyperX Fury', 'Kingston', 10, '16GB', 'DDR4', 17, 3200, 90),
       (4, 'Crucial Ballistix', 'Crucial', 9, '16GB', 'DDR4', 16, 3600, 110);