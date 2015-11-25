CREATE TABLE "Peca" (
	"peca_codigo" bigint NOT NULL,
	"peca_descricao" TEXT(50) NOT NULL,
	"peca_valor_compra" money NOT NULL,
	"peca_valor_venda" money NOT NULL,
	"peca_quantidade" integer NOT NULL,
	"cate_id" bigint NOT NULL,
	"fabr_id" TEXT(15) NOT NULL,
	CONSTRAINT Peca_pk PRIMARY KEY ("peca_codigo")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Categoria" (
	"cate_id" bigint NOT NULL,
	"cate_nome" TEXT(15) NOT NULL,
	"cate_id" bigint(15),
	CONSTRAINT Categoria_pk PRIMARY KEY ("cate_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Fornecedor" (
	"forn_id" bigint NOT NULL,
	"forn_telefone" TEXT(15) NOT NULL,
	"forn_nome_vendedor" TEXT(30) NOT NULL,
	"ende_id" bigint NOT NULL,
	CONSTRAINT Fornecedor_pk PRIMARY KEY ("forn_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Endereco" (
	"ende_id" bigint NOT NULL,
	"logr_id" bigint NOT NULL,
	"ende_logradouro" TEXT(30) NOT NULL,
	"ende_numero" TEXT(10) NOT NULL,
	"ende_complemento" TEXT(15),
	"ende_bairro" TEXT(30) NOT NULL,
	"ende_cidade" TEXT(30) NOT NULL,
	"ende_estado" TEXT(30) NOT NULL,
	"ende_cep" TEXT(30) NOT NULL,
	"ende_cep" TEXT(9) NOT NULL,
	CONSTRAINT Endereco_pk PRIMARY KEY ("ende_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Logradouro" (
	"logr_id" bigint NOT NULL,
	"logr_tipo" TEXT(15) NOT NULL,
	CONSTRAINT Logradouro_pk PRIMARY KEY ("logr_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Peca_Fornecedor" (
	"peca_codigo" bigint NOT NULL,
	"forn_id" bigint NOT NULL,
	CONSTRAINT Peca_Fornecedor_pk PRIMARY KEY ("peca_codigo","forn_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Cliente" (
	"clie_id" bigint NOT NULL,
	"clie_cpf" TEXT NOT NULL,
	"clie_nome" TEXT NOT NULL,
	"clie_telefone" TEXT NOT NULL,
	"ende_id" TEXT NOT NULL,
	CONSTRAINT Cliente_pk PRIMARY KEY ("clie_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Carro" (
	"carr_id" bigint NOT NULL,
	"carr_marca" TEXT NOT NULL,
	"carr_modelo" TEXT NOT NULL,
	"carr_ano" int NOT NULL,
	"carr_cor" TEXT NOT NULL,
	"carr_placa" TEXT NOT NULL,
	"carr_placa" TEXT NOT NULL,
	"clie_id" TEXT NOT NULL,
	CONSTRAINT Carro_pk PRIMARY KEY ("carr_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Orcamento" (
	"orca_id" bigint NOT NULL,
	"orca_valor" money NOT NULL,
	"orca_mao_obra" money NOT NULL,
	"orca_data" DATE NOT NULL,
	"carr_id" bigint NOT NULL,
	CONSTRAINT Orcamento_pk PRIMARY KEY ("orca_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Raparo" (
	"repa_id" bigint NOT NULL,
	"repa_descricao_breve" TEXT NOT NULL,
	"repa_descricao_detalhada" TEXT NOT NULL,
	"repa_valor" money NOT NULL,
	"repa_tempo_execucao" int NOT NULL,
	CONSTRAINT Raparo_pk PRIMARY KEY ("repa_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Reparo_Peca" (
	"peca_codigo" bigint NOT NULL,
	"peca_id" bigint NOT NULL,
	CONSTRAINT Reparo_Peca_pk PRIMARY KEY ("peca_codigo","peca_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Fabricante" (
	"fabr_id" bigint NOT NULL,
	"fabr_telefone" TEXT(15) NOT NULL,
	CONSTRAINT Fabricante_pk PRIMARY KEY ("fabr_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Servico" (
	"serv_id" bigint NOT NULL,
	"orca_id" bigint NOT NULL,
	CONSTRAINT Servico_pk PRIMARY KEY ("serv_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Orcamento_Reparo" (
	"orca_id" bigint NOT NULL,
	"repa_id" bigint NOT NULL,
	CONSTRAINT Orcamento_Reparo_pk PRIMARY KEY ("orca_id","repa_id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "Peca" ADD CONSTRAINT "Peca_fk0" FOREIGN KEY (cate_id) REFERENCES Categoria(cate_id);
ALTER TABLE "Peca" ADD CONSTRAINT "Peca_fk1" FOREIGN KEY (fabr_id) REFERENCES Fabricante(fabr_id);

ALTER TABLE "Categoria" ADD CONSTRAINT "Categoria_fk0" FOREIGN KEY (cate_id) REFERENCES Categoria(cate_id);

ALTER TABLE "Fornecedor" ADD CONSTRAINT "Fornecedor_fk0" FOREIGN KEY (ende_id) REFERENCES Endereco(ende_id);

ALTER TABLE "Endereco" ADD CONSTRAINT "Endereco_fk0" FOREIGN KEY (logr_id) REFERENCES Logradouro(logr_id);


ALTER TABLE "Peca_Fornecedor" ADD CONSTRAINT "Peca_Fornecedor_fk0" FOREIGN KEY (peca_codigo) REFERENCES Peca(peca_codigo);
ALTER TABLE "Peca_Fornecedor" ADD CONSTRAINT "Peca_Fornecedor_fk1" FOREIGN KEY (forn_id) REFERENCES Fornecedor(forn_id);

ALTER TABLE "Cliente" ADD CONSTRAINT "Cliente_fk0" FOREIGN KEY (ende_id) REFERENCES Endereco(ende_id);

ALTER TABLE "Carro" ADD CONSTRAINT "Carro_fk0" FOREIGN KEY (clie_id) REFERENCES Cliente(clie_id);

ALTER TABLE "Orcamento" ADD CONSTRAINT "Orcamento_fk0" FOREIGN KEY (carr_id) REFERENCES Carro(carr_id);


ALTER TABLE "Reparo_Peca" ADD CONSTRAINT "Reparo_Peca_fk0" FOREIGN KEY (peca_codigo) REFERENCES Peca(peca_codigo);
ALTER TABLE "Reparo_Peca" ADD CONSTRAINT "Reparo_Peca_fk1" FOREIGN KEY (peca_id) REFERENCES Raparo(repa_id);


ALTER TABLE "Servico" ADD CONSTRAINT "Servico_fk0" FOREIGN KEY (orca_id) REFERENCES Orcamento(orca_id);

ALTER TABLE "Orcamento_Reparo" ADD CONSTRAINT "Orcamento_Reparo_fk0" FOREIGN KEY (orca_id) REFERENCES Orcamento(orca_id);
ALTER TABLE "Orcamento_Reparo" ADD CONSTRAINT "Orcamento_Reparo_fk1" FOREIGN KEY (repa_id) REFERENCES Raparo(repa_id);

