-- RICETTE
INSERT INTO ricette (name, ingredients, image, preparation_time, num_of_portions, description) VALUES ('Cake', '200g Farina, 5 Uova', 'https://www.cucchiaio.it/content/dam/cucchiaio/it/ricette/2023/05/torta-chantilly/torta-chantilly-aperture.jpg', 20, 1, 'Torta semplice al cioccolato');
INSERT INTO ricette (name, ingredients, image, preparation_time, num_of_portions, description) VALUES ('Spaghetti Bolognese', '400g Pasta, 300g Carne macinata, Pomodoro', 'https://supervalu.ie/thumbnail/800x600/var/files/real-food/recipes/Uploaded-2020/spaghetti-bolognese-recipe.jpg', 30, 2, 'Classica ricetta di spaghetti al ragù.');
INSERT INTO ricette (name, ingredients, image, preparation_time, num_of_portions, description) VALUES ('Insalata Caprese', 'Pomodori, Mozzarella, Basilico', 'https://media-assets.lacucinaitaliana.it/photos/64b938d160df211dd0c14782/16:9/w_1920,c_limit/shutterstock_508377580.jpg', 10, 4, 'Insalata fresca con pomodori, mozzarella e basilico.');

-- CATEGORIE
INSERT INTO categorie (name) VALUES ('Dolci');
INSERT INTO categorie (name) VALUES ('Primi Piatti');
INSERT INTO categorie (name) VALUES ('Insalate');
INSERT INTO categorie (name) VALUES ('Secondi Piatti');