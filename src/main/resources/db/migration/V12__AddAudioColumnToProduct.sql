alter table shop.products add column audio varchar(255);
update shop.products set audio = '/audio/autumn begins.mp3' where id = 1;
update shop.products set audio = '/audio/Chill.mp3' where id = 2;
update shop.products set audio = '/audio/FEEL THE VIBE.mp3' where id = 3;
update shop.products set audio = '/audio/LA.mp3' where id = 4;
update shop.products set audio = '/audio/Senorita.mp3' where id = 5;
update shop.products set audio = '/audio/summer ends.mp3' where id = 6;
update shop.products set audio = '/audio/With you.mp3' where id = 7;