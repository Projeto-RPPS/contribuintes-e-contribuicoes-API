ALTER TABLE contribuicao ADD COLUMN data_referencia DATE;

ALTER TABLE contribuicao DROP COLUMN mes_referente;
ALTER TABLE contribuicao DROP COLUMN ano_referente;