SELECT DISTINCT on (C.carr_marca) C.carr_marca, A.repa_descricao_breve, frequencia from carro C JOIN (
  SELECT DISTINCT
    (C2.carr_marca) marca,
    R.repa_descricao_breve,
    count(*) frequencia
  FROM carro C2 NATURAL JOIN orcamento O NATURAL JOIN orcamento_reparo ORC NATURAL JOIN reparo R
  GROUP BY marca, R.repa_descricao_breve ORDER BY marca, frequencia DESC
) A on A.marca = C.carr_marca;