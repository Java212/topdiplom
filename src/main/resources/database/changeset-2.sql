INSERT INTO public.users(user_id, user_name, password) VALUES (1, 'user', '1234');
INSERT INTO public.addresses(address_id, district, street, number_of_house, apartment_number) VALUES (1, 'ЗАО', 'Рябиновая', 1, 2);
INSERT INTO public.users_info(user_info_id, name, email, phone_number, user_id, address_id) VALUES (1, 'user', 'user@mail.ru', '12345678', 1, 1);
INSERT INTO public.categories(category_id, title) VALUES (1, 'УШМ');
INSERT INTO public.categories(category_id, title) VALUES (2, 'Перфораторы');
INSERT INTO public.categories(category_id, title) VALUES (3, 'Аккумуляторный инструмент');
INSERT INTO public.products(product_id, category_id, user_info_id, title, link_to_the_image, specification, price, is_busy) VALUES (1, 1, 1, 'УШМ',
 'https://d1mv2b9v99cq0i.cloudfront.net/eyJidWNrZXQiOiJ3ZWItbmluamEtaW1hZ2VzIiwia2V5IjoiaG9sbWVzYmVhcmluZ3NcL2ltYWdlc1wvcHJvZGltZ1wvNTcyNF8xLnBuZyIsImVkaXRzIjp7InJlc2l6ZSI6eyJ3aWR0aCI6MTIwMCwiaGVpZ2h0Ijo2MzAsImZpdCI6Imluc2lkZSIsIndpdGhvdXRFbmxhcmdlbWVudCI6dHJ1ZX19LCJ2ZXJzaW9uIjoiMzAxNWI2NjU5MTZkMDZkNTA4ODZlZmZjZGRmMmRhNjQ4MjM3OTIzNiJ9',
 ' Небольшой пример текста, который должен основываться на названии карточки и составлять основную часть содержимого карты', 234, 'false');
INSERT INTO public.products(product_id, category_id, user_info_id, title, link_to_the_image, specification, price, is_busy) VALUES (2, 1, 1, 'УШМ',
 'https://svarnov.ru/storage/.thumbs/preview_watermark09e7f4bc67cc63972373706452603edf.jpeg',
  ' Небольшой пример текста, который должен основываться на названии карточки и составлять основную часть содержимого карты', 234, 'false');
INSERT INTO public.products(product_id, category_id, user_info_id, title, link_to_the_image, specification, price, is_busy) VALUES (3, 1, 1, 'УШМ',
 'https://tehno-leader.ru/upload/iblock/954/95463fb34bcd6d0678fd6d07aebc7464.jpg',
  ' Небольшой пример текста, который должен основываться на названии карточки и составлять основную часть содержимого карты', 234, 'false');
INSERT INTO public.products(product_id, category_id, user_info_id, title, link_to_the_image, specification, price, is_busy) VALUES (4, 1, 1, 'УШМ',
 'https://cdn.shopify.com/s/files/1/0734/3643/products/495-6088-30_HR_grande.png?v=1578610601',
  ' Небольшой пример текста, который должен основываться на названии карточки и составлять основную часть содержимого карты', 234, 'false');
INSERT INTO public.products(product_id, category_id, user_info_id, title, link_to_the_image, specification, price, is_busy) VALUES (5, 1, 1, 'УШМ',
 'https://kanion-sochi.ru/upload/iblock/583/yfs5ejr8zf9rcqw0f3ryvjfvfh5rcja0/76718417_20d4_11de_822d_001bb9bd9c74_962e2258_3546_11e7_93f3_e61f13e05487.jpeg',
  ' Небольшой пример текста, который должен основываться на названии карточки и составлять основную часть содержимого карты', 234, 'false');
INSERT INTO public.products(product_id, category_id, user_info_id, title, link_to_the_image, specification, price, is_busy) VALUES (6, 2, 1, 'Перфоратор',
 'https://hotwell.kz/vsei/wp-content/uploads/sites/11/2022/07/3erkbor2tfx0h804kxg6fn6e81qhk1mv-600x600.jpg',
  ' Небольшой пример текста, который должен основываться на названии карточки и составлять основную часть содержимого карты', 234, 'false');
INSERT INTO public.products(product_id, category_id, user_info_id, title, link_to_the_image, specification, price, is_busy) VALUES (7, 2, 1, 'Перфоратор',
 'https://njuans.ru/upload/iblock/fb4/i5w6xc0228hud3omiibfb7353ck9hdlo.jpg',
  ' Небольшой пример текста, который должен основываться на названии карточки и составлять основную часть содержимого карты', 234, 'false');
INSERT INTO public.products(product_id, category_id, user_info_id, title, link_to_the_image, specification, price, is_busy) VALUES (8, 2, 1, 'Перфоратор',
 'https://www.705507.ru/img_prod/1602.jpg',
  ' Небольшой пример текста, который должен основываться на названии карточки и составлять основную часть содержимого карты', 234, 'false');
INSERT INTO public.products(product_id, category_id, user_info_id, title, link_to_the_image, specification, price, is_busy) VALUES (9, 2, 1, 'Перфоратор',
 'https://trudogolik24.ru/pic/tov/1512546183.jpg',
  ' Небольшой пример текста, который должен основываться на названии карточки и составлять основную часть содержимого карты', 234, 'false');
INSERT INTO public.products(product_id, category_id, user_info_id, title, link_to_the_image, specification, price, is_busy) VALUES (10, 2, 1, 'Перфоратор',
 'https://dokamir.com.ua/image/data/985_1.jpg',
  ' Небольшой пример текста, который должен основываться на названии карточки и составлять основную часть содержимого карты', 234, 'false');
INSERT INTO public.products(product_id, category_id, user_info_id, title, link_to_the_image, specification, price, is_busy) VALUES (11, 3, 1, 'Шуруповерт',
 'https://images.thdstatic.com/productImages/f457c0d6-987f-495f-80b7-e1c36a479cf1/svn/dewalt-power-tool-combo-kits-dck240c2-c3_600.jpg',
  ' Небольшой пример текста, который должен основываться на названии карточки и составлять основную часть содержимого карты', 234, 'false');
INSERT INTO public.products(product_id, category_id, user_info_id, title, link_to_the_image, specification, price, is_busy) VALUES (12, 3, 1, 'Шуруповерт',
 'https://tool-24.ru/upload/iblock/50f/lisu623u7hz2r8fxtkfbgb7zb50yhx5m.jpg',
  ' Небольшой пример текста, который должен основываться на названии карточки и составлять основную часть содержимого карты', 234, 'false');
INSERT INTO public.products(product_id, category_id, user_info_id, title, link_to_the_image, specification, price, is_busy) VALUES (13, 3, 1,'Шуруповерт',
 'https://ae01.alicdn.com/kf/H0cee0376da974fb8a7e667b43e65bb34E/Bort-BAB-18-Ux2Li-FDK-98296839.jpeg',
  ' Небольшой пример текста, который должен основываться на названии карточки и составлять основную часть содержимого карты', 234, 'false');
INSERT INTO public.products(product_id, category_id, user_info_id, title, link_to_the_image, specification, price, is_busy) VALUES (14, 3, 1, 'Шуруповерт',
 'https://50.by/files/products/11_103.800x600w.jpg',
  ' Небольшой пример текста, который должен основываться на названии карточки и составлять основную часть содержимого карты', 234, 'false');
INSERT INTO public.products(product_id, category_id, user_info_id, title, link_to_the_image, specification, price, is_busy) VALUES (15, 3, 1, 'Шуруповерт',
 'https://tools-brn.ru/242-large_default/drel-shurupovert-akkumulyatornaya-psr-12.jpg',
  ' Небольшой пример текста, который должен основываться на названии карточки и составлять основную часть содержимого карты', 234, 'false');