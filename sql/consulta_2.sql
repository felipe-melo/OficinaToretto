SELECT DISTINCT on (C.carr_modelo, C.carr_ano) C.carr_modelo, C.carr_ano, frequencia from carro C JOIN (
  SELECT DISTINCT
    (C2.carr_modelo) modelo, C2.carr_ano ano,
    R.repa_descricao_breve,
    count(*) frequencia
  FROM carro C2 NATURAL JOIN orcamento O NATURAL JOIN orcamento_reparo ORC NATURAL JOIN reparo R
  GROUP BY modelo, R.repa_descricao_breve ORDER BY modelo, frequencia DESC
) A on A.modelo = C.carr_modelo AND A.ano = C.carr_ano;