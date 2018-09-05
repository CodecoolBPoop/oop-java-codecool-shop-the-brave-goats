DELETE FROM products *;

INSERT INTO public.product_categories(id, name) VALUES (1,'weapons');
INSERT INTO public.product_categories(id, name) VALUES (2, 'fakeDegrees');

INSERT INTO public.supplier(id, name) VALUES (1, 'North Korea');
INSERT INTO public.supplier(id, name) VALUES (2, 'Russia');
INSERT INTO public.supplier(id, name) VALUES (3, 'Hungary');
INSERT INTO public.supplier(id, name) VALUES (4, 'USA');


INSERT INTO public.products( name, price, currency, supplier, product_category, description)VALUES ( 'Nuclear Resources', 900000, 'USD', 1, 1,'Fantastic price. Large content ecosystem. Helpful technical support.');
INSERT INTO public.products( name, price, currency, supplier, product_category, description)VALUES ( 'Kalashnikov AK-47', 4000, 'USD', 2, 1, 'A gas-operated, 7.62 * 39 mm assault rifle.');
INSERT INTO public.products( name, price, currency, supplier, product_category, description)VALUES ( 'Hit Man', 18000, 'USD', 2, 1, 'The toughest guys from the Russian mob.');
INSERT INTO public.products( name, price, currency, supplier, product_category, description)VALUES ( 'Green Fox Academy', 6, 'USD', 3, 2, 'What does the fox say?');
INSERT INTO public.products( name, price, currency, supplier, product_category, description)VALUES ( 'Codecool OOP Exam Pass', 100, 'USD', 3, 2, 'OOPs, I passed it again.');
INSERT INTO public.products( name, price, currency, supplier, product_category, description)VALUES ( 'Theology of the Flying Spaghetti Monster', 10500, 'USD', 4, 2, 'Holy Pastafarianism degree by the Church of the Flying Spaghetti Monster.');
