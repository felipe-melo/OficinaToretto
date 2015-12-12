SELECT cli1.clie_nome
FROM cliente cli1 where not exists(
    (SELECT rep.repa_id from reparo rep) EXCEPT
    (SELECT Rep.repa_id from
      cliente cli2 NATURAL JOIN carro NATURAL JOIN orcamento NATURAL JOIN orcamento_reparo NATURAL JOIN reparo Rep where cli1.clie_id = cli2.clie_id)
);