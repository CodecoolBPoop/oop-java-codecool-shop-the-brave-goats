DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS addresses;
DROP TABLE IF EXISTS shopping_carts;
DROP TABLE IF EXISTS order_histories;
DROP TABLE IF EXISTS product_categories;

CREATE TABLE product_categories(
  id INTEGER PRIMARY KEY not null,
  name TEXT,
  department TEXT,
  description TEXT
);

CREATE TABLE supplier(
  id INTEGER PRIMARY KEY NOT NULL,
  name TEXT,
  description TEXT
);

CREATE TABLE products(
  id INTEGER PRIMARY KEY NOT NULL,
  name VARCHAR(200) NOT NULL,
  price INTEGER NOT NULL,
  currency TEXT,
  supplier INTEGER NOT NULL, /*  THis is a foreign key */
  product_category INTEGER NOT NULL, /*  THis is a foreign key */
  description TEXT
);

CREATE TABLE users(
  email VARCHAR(50) PRIMARY KEY NOT NULL,
  password VARCHAR(50) NOT NULL,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  phone INTEGER NOT NULL
);

CREATE TABLE addresses(
  id INTEGER PRIMARY KEY NOT NULL,
  user_email VARCHAR(50) NOT NULL, /* Ez itt egy foreign key */
  billing_address VARCHAR(10),
  shipping_address VARCHAR(10),
  country TEXT NOT NULL,
  state TEXT NOT NULL,
  city TEXT NOT NULL,
  zip INTEGER NOT NULL,
  street TEXT NOT NULL,
  house_number TEXT NOT NULL
);

CREATE TABLE shopping_carts(
  id INTEGER PRIMARY KEY NOT NULL,
  user_email VARCHAR(50) NOT NULL, /* Ez itt egy foreign key */
  product_id INTEGER NOT NULL, /* Ez itt egy foreign key */
  product_quantity INTEGER NOT NULL,
  total_price INTEGER NOT NULL
);

CREATE TABLE order_histories(
  id INTEGER PRIMARY KEY NOT NULL,
  user_email VARCHAR(50) NOT NULL, /* Ez itt egy foreign key */
  product_id INTEGER NOT NULL, /* Ez itt egy foreign key */
  product_quantity INTEGER NOT NULL,
  order_date DATE NOT NULL,
  payment_method TEXT,
  total_price INTEGER NOT NULL
);

ALTER TABLE ONLY addresses
  ADD CONSTRAINT fk_addresses_users_email FOREIGN KEY (user_email) REFERENCES users(email);

ALTER TABLE ONLY products
  ADD CONSTRAINT fk_product_supplier_id FOREIGN KEY (supplier) REFERENCES supplier(id);

ALTER TABLE ONLY products
  ADD CONSTRAINT fk_product_product_categories_id_ FOREIGN KEY (product_category) REFERENCES product_categories(id);

ALTER TABLE ONLY shopping_carts
  ADD CONSTRAINT fk_shopping_carts_users_email FOREIGN KEY (user_email) REFERENCES users(email);

ALTER TABLE ONLY shopping_carts
  ADD CONSTRAINT fk_shopping_carts_product_id_id FOREIGN KEY (product_id) REFERENCES products(id);

ALTER TABLE ONLY order_histories
  ADD CONSTRAINT fk_order_history_users_email FOREIGN KEY (user_email) REFERENCES users(email);

ALTER TABLE ONLY order_histories
  ADD CONSTRAINT fk_order_history_products_id FOREIGN KEY (product_id) REFERENCES products(id);












