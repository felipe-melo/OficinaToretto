CREATE TABLE "peca" (
	"peca_codigo" bigint NOT NULL,
	"peca_descricao" TEXT NOT NULL,
	"peca_valor_compra" money NOT NULL,
	"peca_valor_venda" money NOT NULL,
	"peca_quantidade" integer NOT NULL,
	"cate_id" bigint NOT NULL,
	"fabr_id" bigint NOT NULL,
	CONSTRAINT peca_pk PRIMARY KEY ("peca_codigo")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "categoria" (
	"cate_id" bigint NOT NULL,
	"cate_nome" TEXT NOT NULL,
	"cate_super_id" bigint,
	CONSTRAINT categoria_pk PRIMARY KEY ("cate_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "fornecedor" (
	"forn_id" bigint NOT NULL,
	"forn_telefone" TEXT NOT NULL,
	"forn_nome_vendedor" TEXT NOT NULL,
	"ende_id" bigint NOT NULL,
	CONSTRAINT fornecedor_pk PRIMARY KEY ("forn_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "endereco" (
	"ende_id" bigint NOT NULL,
	"logr_id" bigint NOT NULL,
	"ende_logradouro" TEXT NOT NULL,
	"ende_numero" TEXT NOT NULL,
	"ende_complemento" TEXT,
	"ende_bairro" TEXT NOT NULL,
	"ende_cidade" TEXT NOT NULL,
	"ende_estado" TEXT NOT NULL,
	"ende_cep" TEXT NOT NULL,
	CONSTRAINT endereco_pk PRIMARY KEY ("ende_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "logradouro" (
	"logr_id" bigint NOT NULL,
	"logr_tipo" TEXT NOT NULL,
	CONSTRAINT logradouro_pk PRIMARY KEY ("logr_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "peca_fornecedor" (
	"peca_codigo" bigint NOT NULL,
	"forn_id" bigint NOT NULL,
	CONSTRAINT peca_fornecedor_pk PRIMARY KEY ("peca_codigo","forn_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "cliente" (
	"clie_id" bigint NOT NULL,
	"clie_cpf" TEXT NOT NULL,
	"clie_nome" TEXT NOT NULL,
	"clie_telefone" TEXT NOT NULL,
	"ende_id" bigint NOT NULL,
	CONSTRAINT cliente_pk PRIMARY KEY ("clie_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "carro" (
	"carr_id" bigint NOT NULL,
	"carr_marca" TEXT NOT NULL,
	"carr_modelo" TEXT NOT NULL,
	"carr_ano" int NOT NULL,
	"carr_cor" TEXT NOT NULL,
	"carr_placa" TEXT NOT NULL,
	"clie_id" bigint NOT NULL,
	CONSTRAINT carro_pk PRIMARY KEY ("carr_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "orcamento" (
	"orca_id" bigint NOT NULL,
	"orca_valor" money NOT NULL,
	"orca_mao_obra" money NOT NULL,
	"orca_data" DATE NOT NULL,
	"carr_id" bigint NOT NULL,
	CONSTRAINT orcamento_pk PRIMARY KEY ("orca_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "reparo" (
	"repa_id" bigint NOT NULL,
	"repa_descricao_breve" TEXT NOT NULL,
	"repa_descricao_detalhada" TEXT NOT NULL,
	"repa_valor" money NOT NULL,
	"repa_tempo_execucao" int NOT NULL,
	CONSTRAINT reparo_pk PRIMARY KEY ("repa_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "reparo_peca" (
	"peca_codigo" bigint NOT NULL,
	"repa_id" bigint NOT NULL,
	CONSTRAINT reparo_peca_pk PRIMARY KEY ("peca_codigo","repa_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "fabricante" (
	"fabr_id" bigint NOT NULL,
	"fabr_telefone" TEXT NOT NULL,
	CONSTRAINT fabricante_pk PRIMARY KEY ("fabr_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "servico" (
	"serv_id" bigint NOT NULL,
	"orca_id" bigint NOT NULL,
	"paga_id" bigint NOT NULL,
	CONSTRAINT servico_pk PRIMARY KEY ("serv_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "orcamento_reparo" (
	"orca_id" bigint NOT NULL,
	"repa_id" bigint NOT NULL,
	CONSTRAINT orcamento_reparo_pk PRIMARY KEY ("orca_id","repa_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "nota" (
	"nota_id" bigint NOT NULL,
	"nota_numero" int NOT NULL,
	"serv_id" bigint NOT NULL,
	CONSTRAINT nota_pk PRIMARY KEY ("nota_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "pagamento" (
	"paga_id" bigint NOT NULL,
	CONSTRAINT pagamento_pk PRIMARY KEY ("paga_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "cartao_credito" (
	"paga_id" bigint NOT NULL,
	CONSTRAINT cartao_credito_pk PRIMARY KEY ("paga_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "dinheiro" (
	"paga_id" bigint NOT NULL,
	CONSTRAINT dinheiro_pk PRIMARY KEY ("paga_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "cartao_debito" (
	"paga_id" bigint NOT NULL,
	CONSTRAINT cartao_debito_pk PRIMARY KEY ("paga_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "usuario" (
	"usua_id" bigint NOT NULL,
	"usua_username" TEXT NOT NULL,
	"usua_password" TEXT NOT NULL,
	"usua_nome" TEXT NOT NULL,
	CONSTRAINT usuario_pk PRIMARY KEY ("usua_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "gerente" (
	"usua_id" bigint NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "mecanico" (
	"usua_id" bigint NOT NULL
) WITH (
  OIDS=FALSE
);



ALTER TABLE "peca" ADD CONSTRAINT "peca_fk0" FOREIGN KEY (cate_id) REFERENCES categoria(cate_id);
ALTER TABLE "peca" ADD CONSTRAINT "peca_fk1" FOREIGN KEY (fabr_id) REFERENCES fabricante(fabr_id);

ALTER TABLE "categoria" ADD CONSTRAINT "categoria_fk0" FOREIGN KEY (cate_super_id) REFERENCES categoria(cate_id);

ALTER TABLE "fornecedor" ADD CONSTRAINT "fornecedor_fk0" FOREIGN KEY (ende_id) REFERENCES endereco(ende_id);

ALTER TABLE "endereco" ADD CONSTRAINT "endereco_fk0" FOREIGN KEY (logr_id) REFERENCES logradouro(logr_id);


ALTER TABLE "peca_fornecedor" ADD CONSTRAINT "peca_fornecedor_fk0" FOREIGN KEY (peca_codigo) REFERENCES peca(peca_codigo);
ALTER TABLE "peca_fornecedor" ADD CONSTRAINT "peca_fornecedor_fk1" FOREIGN KEY (forn_id) REFERENCES fornecedor(forn_id);

ALTER TABLE "cliente" ADD CONSTRAINT "cliente_fk0" FOREIGN KEY (ende_id) REFERENCES endereco(ende_id);

ALTER TABLE "carro" ADD CONSTRAINT "carro_fk0" FOREIGN KEY (clie_id) REFERENCES cliente(clie_id);

ALTER TABLE "orcamento" ADD CONSTRAINT "orcamento_fk0" FOREIGN KEY (carr_id) REFERENCES carro(carr_id);


ALTER TABLE "reparo_peca" ADD CONSTRAINT "reparo_peca_fk0" FOREIGN KEY (peca_codigo) REFERENCES peca(peca_codigo);
ALTER TABLE "reparo_peca" ADD CONSTRAINT "reparo_peca_fk1" FOREIGN KEY (repa_id) REFERENCES reparo(repa_id);


ALTER TABLE "servico" ADD CONSTRAINT "servico_fk0" FOREIGN KEY (orca_id) REFERENCES orcamento(orca_id);
ALTER TABLE "servico" ADD CONSTRAINT "servico_fk1" FOREIGN KEY (paga_id) REFERENCES pagamento(paga_id);

ALTER TABLE "orcamento_reparo" ADD CONSTRAINT "orcamento_reparo_fk0" FOREIGN KEY (orca_id) REFERENCES orcamento(orca_id);
ALTER TABLE "orcamento_reparo" ADD CONSTRAINT "orcamento_reparo_fk1" FOREIGN KEY (repa_id) REFERENCES reparo(repa_id);

ALTER TABLE "nota" ADD CONSTRAINT "nota_fk0" FOREIGN KEY (serv_id) REFERENCES servico(serv_id);


ALTER TABLE "cartao_credito" ADD CONSTRAINT "cartao_credito_fk0" FOREIGN KEY (paga_id) REFERENCES pagamento(paga_id);

ALTER TABLE "dinheiro" ADD CONSTRAINT "dinheiro_fk0" FOREIGN KEY (paga_id) REFERENCES pagamento(paga_id);

ALTER TABLE "cartao_debito" ADD CONSTRAINT "cartao_debito_fk0" FOREIGN KEY (paga_id) REFERENCES pagamento(paga_id);


ALTER TABLE "gerente" ADD CONSTRAINT "gerente_fk0" FOREIGN KEY (usua_id) REFERENCES usuario(usua_id);

ALTER TABLE "mecanico" ADD CONSTRAINT "mecanico_fk0" FOREIGN KEY (usua_id) REFERENCES usuario(usua_id);

