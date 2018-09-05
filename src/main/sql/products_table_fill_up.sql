DELETE FROM products *;

INSERT INTO public.product_categories(id, name, department, description) VALUES (1,'weapons', 'Well see', 'Weapons');
INSERT INTO public.product_categories(id, name, department, description) VALUES (2, 'fakeDegrees', 'Well see', 'Fake degrees');

INSERT INTO public.supplier(id, name, description) VALUES (1, 'North Korea', 'Dictatorial regime.');
INSERT INTO public.supplier(id, name, description) VALUES (2, 'Russia', 'Wannabe superpower.');
INSERT INTO public.supplier(id, name, description) VALUES (3, 'Hungary', 'Illiberal catholic democracy.');
INSERT INTO public.supplier(id, name, description) VALUES (4, 'USA', 'Great again.');


INSERT INTO public.products( name, price, currency, supplier, product_category, description)VALUES ( 'Nuclear Resources', 900000, 'USD', 1, 1,'Fantastic price. Large content ecosystem. Helpful technical support.');
INSERT INTO public.products( name, price, currency, supplier, product_category, description)VALUES ( 'Kalashnikov AK-47', 4000, 'USD', 2, 1, 'A gas-operated, 7.62 * 39 mm assault rifle.');
INSERT INTO public.products( name, price, currency, supplier, product_category, description)VALUES ( 'Hit Man', 18000, 'USD', 2, 1, 'The toughest guys from the Russian mob.');
INSERT INTO public.products( name, price, currency, supplier, product_category, description)VALUES ( 'Green Fox Academy', 6, 'USD', 3, 2, 'What does the fox say?');
INSERT INTO public.products( name, price, currency, supplier, product_category, description)VALUES ( 'Codecool OOP Exam Pass', 100, 'USD', 3, 2, 'OOPs, I passed it again.');
INSERT INTO public.products( name, price, currency, supplier, product_category, description)VALUES ( 'Theology of the Flying Spaghetti Monster', 10500, 'USD', 4, 2, 'Holy Pastafarianism degree by the Church of the Flying Spaghetti Monster.');
