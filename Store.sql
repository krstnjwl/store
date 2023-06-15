CREATE SCHEMA store;

SET SCHEMA 'store';

CREATE TABLE users (
	username VARCHAR(255) NOT NULL,
	first_name VARCHAR(255) NOT NULL,
	last_name VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL,
	pword VARCHAR(255) NOT NULL,
	acc_type INTEGER NOT NULL,
		PRIMARY KEY (username)
);

CREATE TABLE items (
	item_id SERIAL NOT NULL,
	picture VARCHAR(255) NOT NULL,
	item_name VARCHAR(255) NOT NULL,
	category VARCHAR(255) NOT NULL,
	seller_name VARCHAR(255) NOT NULL,
	description TEXT NOT NULL,
	price INTEGER NOT NULL,
	stocks INTEGER NOT NULL,
		PRIMARY KEY (item_id)
);

CREATE TABLE cart (
	item_no SERIAL NOT NULL,
	buyer_name VARCHAR(255) NOT NULL,
	item_id INTEGER NOT NULL,
	item_name VARCHAR(255) NOT NULL,
	category VARCHAR(255) NOT NULL,
	seller_name VARCHAR(255) NOT NULL,
	price INTEGER NOT NULL,
	amount INTEGER NOT NULL,
	total_cost INTEGER NOT NULL,
		PRIMARY KEY (item_no)
);

CREATE TABLE sold (
	transaction_no SERIAL NOT NULL,
	buyer_name VARCHAR(255),
	item_id INTEGER NOT NULL,
	item_name VARCHAR(255) NOT NULL,
	category VARCHAR(255) NOT NULL,
	seller_name VARCHAR(255) NOT NULL,
	price INTEGER NOT NULL,
	amount INTEGER NOT NULL,
	transaction_cost INTEGER NOT NULL,
	payment_method VARCHAR(255) NOT NULL,
	address VARCHAR(255) NOT NULL,
	order_status VARCHAR(255) NOT NULL,
		PRIMARY KEY (transaction_no)
);

CREATE OR REPLACE FUNCTION ADDTOCART(buyer VARCHAR(255), itemid INTEGER, amt INTEGER)
RETURNS VOID AS $$
	DECLARE
		itemname VARCHAR(255);
		categ VARCHAR(255);
		seller VARCHAR(255);
		prc INTEGER;
		icost INTEGER;
		stock INTEGER;
		curramt INTEGER;
		newamt INTEGER;
	BEGIN
		SELECT item_name, category, seller_name, price, stocks INTO itemname, categ, seller, prc, stock
		FROM items
		WHERE item_id = itemid;
		
		IF EXISTS (
			SELECT 1
			FROM cart
			WHERE buyer_name = buyer AND item_id = itemid
    	) THEN
			SELECT amount INTO curramt
			FROM cart
			WHERE buyer_name = buyer AND item_id = itemid;

			newamt := amt + curramt;
			icost := newamt * prc;

			UPDATE cart
			SET amount = newamt, total_cost = icost
			WHERE buyer_name = buyer AND item_id = itemid;
		ELSE		
			icost := amt * prc;

			INSERT INTO cart(buyer_name, item_id, item_name, category, seller_name, price, amount, total_cost)
			VALUES(buyer, itemid, itemname, categ, seller, prc, amt, icost);
		END IF;
	END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION CHECKOUT(buyer VARCHAR(255), payment VARCHAR(255), address VARCHAR(255))
RETURNS VOID AS $$
	DECLARE
		cart_row cart%ROWTYPE;
		item_row items%ROWTYPE;
	BEGIN
		FOR cart_row IN SELECT * FROM cart WHERE buyer_name = buyer LOOP
			SELECT * INTO item_row FROM items WHERE item_id = cart_row.item_id;

			IF cart_row.amount <= item_row.stocks THEN
				UPDATE items SET stocks = stocks - cart_row.amount WHERE item_id = cart_row.item_id;

				INSERT INTO sold(buyer_name, item_id, item_name, category, seller_name, price, amount, transaction_cost, payment_method, address, order_status)
				VALUES (cart_row.buyer_name, cart_row.item_id, cart_row.item_name, cart_row.category, cart_row.seller_name, cart_row.price, cart_row.amount, cart_row.total_cost, payment, address, 'Preparing order');

				DELETE FROM cart WHERE item_no = cart_row.item_no;
			END IF;
		END LOOP;
	END;
$$ LANGUAGE plpgsql;

INSERT INTO items (item_id, picture, item_name, category, seller_name, description, price, stocks)
VALUES
	(01, 'razerBlade14.jpg', 'Razer Blade 14', 'Laptop', 'Razer', '14-inch Gaming Laptop with AMD Ryzen™ 6900HX', 1799.99, 40),
	(02, 'razerBlade15.jpg', 'Razer Blade 15', 'Laptop', 'Razer','NVIDIA® GeForce RTX™ 40 Series GPUs, 13th Gen Intel® Core™ i7 Processor (14-Core)', 1999.99, 30),
	(03, 'razerBlade16.jpg', 'Razer Blade 16', 'Laptop', 'Razer','NVIDIA® GeForce RTX™ 40 Series 16” Laptop with 13th Gen Intel® Core™ i9 Processor (24-Core)', 2699.99, 50),
	(04, 'razerBlade17.jpg', 'Razer Blade 17', 'Laptop', 'Razer','NVIDIA® GeForce RTX™ 30 Series 17” Laptop with 12th Gen Intel® Core™ i9 Processor (14-Core)', 2299.99, 40),
	(05, 'razerBlade18.jpg', 'Razer Blade 18', 'Laptop', 'Razer','NVIDIA® GeForce RTX™ 40 Series 18” Laptop with 13th Gen Intel® Core™ i9 Processor (24-Core)', 2899.99, 50),
	(06, 'razerBasiliskV3.jpg', 'Razer Basilisk V3', 'Mouse', 'Razer','Customizable Wireless Gaming Mouse with Razer HyperScroll Tilt Wheel', 159.99, 30),
	(07, 'razerDeathAdderV3.jpg', 'Razer DeathAdder V3', 'Mouse', 'Razer','Ultra-lightweight Ergonomic Esports Mouse', 69.99, 40),
	(08, 'razerNagaV2Pro.jpg', 'Razer Naga V2 Pro', 'Mouse', 'Razer','MMO Wireless Gaming Mouse', 179.99, 50),
	(09, 'razerViperMiniSignatureEdition.jpg', 'Razer Viper Mini Signature Edition', 'Mouse', 'Razer','The Best Lightweight Performance Gaming Mouse', 279.99, 30),
	(10, 'razerBlackWidowV3Black.jpg', 'Razer BlackWidow V3 Black', 'Keyboard', 'Razer','Mechanical Gaming Keyboard with Razer Chroma RGB', 179.99, 40),
	(11, 'razerBlackWidowV3Quartz.jpg', 'Razer BlackWidow V3 Quartz', 'Keyboard', 'Razer','Mechanical Gaming Keyboard with Razer Chroma RGB', 179.99, 30),
	(12, 'razerBlackWidowV3BAPE.jpg', 'Razer BlackWidow V3 BAPE', 'Keyboard', 'Razer','Mechanical Gaming Keyboard with Razer Chroma RGB', 179.99, 50),
	(13, 'razerBlackWidowV3EVISU.jpg', 'Razer BlackWidow V3 EVISU', 'Keyboard', 'Razer','Mechanical Gaming Keyboard with Razer Chroma RGB', 179.99, 40),
	(14, 'razerBlackWidowV3HaloInfinite.jpg', 'Razer BlackWidow V3 Halo Infinite', 'Keyboard','Razer', 'Mechanical Gaming Keyboard with Razer Chroma RGB', 179.99, 50),
	(15, 'razerBlackWidowV3Roblox.jpg', 'Razer BlackWidow V3 Robox', 'Keyboard','Razer', 'Mechanical Gaming Keyboard with Razer Chroma RGB', 179.99, 30),
	(16, 'razerDeathStalkerV2.jpg', 'Razer DeathStalker V2', 'Keyboard','Razer', 'Low-Profile RGB Optical Gaming Keyboard', 199.99, 40),
	(17, 'razerHuntsmanV2.jpg', 'Razer Huntsman V2', 'Keyboard','Razer', 'Optical Gaming Keyboard with Near-zero Input Latency', 189.99, 30),
	(18, 'razerNommoV2.jpg', 'Razer Nommo V2', 'Audio','Razer', 'Full-Range 2.1 PC Gaming Speakers with Wireless Subwoofer', 449.99, 50),
	(19, 'razerBlacksharkV2Pro.jpg', 'Razer Blackshark V2 Pro', 'Audio','Razer', 'Wireless Esports Headset', 199.99, 40),
	(20, 'razerBarracudaPro.jpg', 'Razer Barracuda Pro', 'Audio', 'Razer', 'Wireless Gaming Headset with Hybrid ANC', 249.99, 50),
	(21, 'razerKrakenV3.jpg', 'Razer Kraken V3', 'Audio','Razer', 'Wireless Gaming Headset with Haptic Technology', 199.99, 30),
	(22, 'razerWolverineV2.jpg', 'Razer Wolverine V2', 'Controller','Razer', 'Wired Gaming Controller for Xbox Series X', 99.99, 40),
	(23, 'razerKishiV2foriPhone.jpg', 'Razer Kishi V2 for iPhone', 'Controller','Razer', 'Universal Mobile Gaming Controller for iPhone', 99.99, 30),
	(24, 'razerKishiV2forAndroid.jpg', 'Razer Kishi V2 for Android', 'Controller','Razer', 'Universal Mobile Gaming Controller for Android', 99.99, 50),
	(25, 'razerChargingPadChroma.jpg', 'Razer Charging Pad Chroma', 'Accessories','Razer', '10W Fast Wireless Charger with Razer Chroma RGB', 59.99, 40),
	(26, 'razerPhoneCoolerChroma.jpg', 'Razer Phone Cooler Chroma', 'Accessories','Razer', 'Smartphone Cooling Fan with Razer Chroma™ RGB', 59.99, 30),
	(27, 'razerArctechProforiPhone13Pro.jpg', 'Razer Arctech Pro for iPhone 13 Pro', 'Accessories','Razer', 'Protective Smartphone Case with Thermaphene™ Cooling Technology', 44.99, 50);


SELECT * FROM items;
SELECT * FROM cart;
SELECT * FROM sold;

-- SELECT ADDTOCART('parinas', 1, 10);
-- SELECT CHECKOUT('parinas');