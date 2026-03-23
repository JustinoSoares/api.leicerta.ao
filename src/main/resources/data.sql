-- Dados de exemplo para a base de dados Lei Certa AO
-- Legislação angolana (dados ilustrativos)

INSERT INTO legislacao (titulo, descricao, tipo, numero, data_publicacao, diario_republica, url_documento, palavras_chave) VALUES
(
    'Constituição da República de Angola',
    'Lei Fundamental do Estado angolano, aprovada em 2010, que consagra os princípios fundamentais do Estado Democrático de Direito.',
    'CONSTITUICAO',
    NULL,
    '2010-02-05',
    'Diário da República nº 23, I Série, de 5 de Fevereiro de 2010',
    'https://www.governo.gov.ao/constituicao.pdf',
    'constituição, direitos fundamentais, estado, cidadania, democracia, angola'
),
(
    'Lei Geral do Trabalho',
    'Lei que regula as relações jurídico-laborais entre trabalhadores e empregadores em Angola.',
    'LEI',
    '7/15',
    '2015-06-15',
    'Diário da República nº 111, I Série, de 15 de Junho de 2015',
    NULL,
    'trabalho, emprego, contrato de trabalho, salário, férias, despedimento, trabalhador, empregador'
),
(
    'Lei das Sociedades Comerciais',
    'Regula a constituição, funcionamento e extinção das sociedades comerciais em Angola.',
    'LEI',
    '1/04',
    '2004-02-13',
    'Diário da República nº 13, I Série, de 13 de Fevereiro de 2004',
    NULL,
    'empresa, sociedade comercial, negócio, sócio, capital social, constituição de empresa'
),
(
    'Código Penal de Angola',
    'Diploma legal que define os crimes, as penas e as medidas de segurança aplicáveis em Angola.',
    'LEI',
    '38/20',
    '2020-11-11',
    'Diário da República nº 170, I Série, de 11 de Novembro de 2020',
    NULL,
    'crime, pena, prisão, delito, furto, roubo, homicídio, fraude, código penal'
),
(
    'Lei de Terras',
    'Define o regime jurídico das terras em Angola, regulando a propriedade, posse e utilização da terra.',
    'LEI',
    '9/04',
    '2004-11-09',
    'Diário da República nº 90, I Série, de 9 de Novembro de 2004',
    NULL,
    'terra, propriedade, terreno, imóvel, posse, arrendamento, habitação'
),
(
    'Código da Família',
    'Regula as relações familiares em Angola, incluindo casamento, divórcio, filiação e adopção.',
    'LEI',
    NULL,
    '1988-02-20',
    NULL,
    NULL,
    'família, casamento, divórcio, filhos, herança, adopção, pensão, alimentos'
),
(
    'Decreto Presidencial sobre o Sistema Nacional de Saúde',
    'Estabelece o quadro geral de organização e funcionamento do sistema nacional de saúde angolano.',
    'DECRETO_PRESIDENCIAL',
    '262/10',
    '2010-11-24',
    'Diário da República nº 224, I Série, de 24 de Novembro de 2010',
    NULL,
    'saúde, sistema de saúde, hospital, médico, SNS'
);

-- Artigos da Constituição da República de Angola
INSERT INTO artigo (numero, titulo, conteudo, legislacao_id) VALUES
(
    1,
    'A República de Angola',
    'Angola é uma República soberana e independente, baseada na dignidade da pessoa humana e na vontade do povo angolano, que tem como objectivo fundamental a construção de uma sociedade livre, justa, democrática, solidária, de paz, igualdade e progresso social.',
    1
),
(
    2,
    'Estado Democrático de Direito',
    'A República de Angola é um Estado Democrático de Direito que tem como fundamentos: a soberania popular; o primado da Constituição e da lei; a separação de poderes e interdependência de funções; a unidade nacional; o pluralismo de expressão e de organização política.',
    1
),
(
    22,
    'Igualdade',
    'Todos são iguais perante a lei. Ninguém pode ser prejudicado, privilegiado, privado de qualquer direito ou isento de qualquer dever em razão da sua ascendência, sexo, raça, etnia, cor, deficiência, língua, local de nascimento, religião, convicções políticas, ideológicas ou filosóficas, grau de instrução, condição económica ou social ou profissão.',
    1
),
(
    23,
    'Não Discriminação',
    'O Estado respeita e protege a personalidade e a dignidade humanas. Os cidadãos angolanos não podem ser privilegiados, beneficiados, prejudicados, privados de nenhum direito ou isentos de nenhum dever por razões de raça, sexo, origem étnica, língua, local de nascimento, religião ou convicções políticas e ideológicas.',
    1
),
(
    30,
    'Direito à Vida',
    'A vida humana é inviolável. O Estado respeita e protege a vida humana. Não há pena de morte em Angola.',
    1
),
(
    56,
    'Direito ao Trabalho',
    'O trabalho é um direito e um dever de todo o cidadão. Todo o cidadão tem o direito ao trabalho, à livre escolha da profissão, a condições de trabalho justas, a remuneração justa e ao descanso.',
    1
),
(
    76,
    'Direito à Educação',
    'O Estado promove e garante o acesso de todos à instrução, ao ensino e à cultura. O ensino público primário é universal, obrigatório e gratuito, nos termos definidos por lei.',
    1
),
(
    77,
    'Direito à Saúde',
    'O Estado promove e garante as medidas necessárias para assegurar a todos o direito à assistência médica e sanitária, bem como o direito à educação física, ao desporto e à valorização da juventude.',
    1
);

-- Artigos da Lei Geral do Trabalho
INSERT INTO artigo (numero, titulo, conteudo, legislacao_id) VALUES
(
    1,
    'Âmbito de Aplicação',
    'A presente lei aplica-se às relações de trabalho subordinado constituídas em território nacional, seja qual for a nacionalidade ou residência das partes, o lugar da celebração do contrato ou a natureza do vínculo.',
    2
),
(
    14,
    'Contrato de Trabalho',
    'Contrato de trabalho é o acordo pelo qual uma pessoa singular, denominada trabalhador, se obriga, mediante retribuição, a prestar a sua actividade a outra pessoa, singular ou colectiva, denominada empregador, sob a autoridade e direcção desta.',
    2
),
(
    80,
    'Retribuição Mínima',
    'O Governo fixa anualmente a retribuição mínima garantida. A retribuição do trabalhador não pode ser inferior à retribuição mínima garantida fixada para a respectiva categoria ou actividade.',
    2
),
(
    104,
    'Direito a Férias',
    'O trabalhador tem direito a um período de férias remuneradas em cada ano civil. O período de férias é de 22 dias úteis por ano, para trabalhadores com mais de dois anos de serviço.',
    2
),
(
    212,
    'Despedimento Ilícito',
    'É ilícito o despedimento efectuado com violação do disposto na presente lei ou com fundamento em motivos políticos, religiosos, étnicos ou por motivos de discriminação de qualquer natureza.',
    2
);

-- Artigos do Código Penal
INSERT INTO artigo (numero, titulo, conteudo, legislacao_id) VALUES
(
    193,
    'Furto',
    'Quem, com ilegítima intenção de apropriação para si ou para outra pessoa, subtrair coisa alheia móvel é punido com pena de prisão até 3 anos ou com pena de multa.',
    4
),
(
    197,
    'Roubo',
    'Quem subtrair, ou constranger a que lhe seja entregue, coisa alheia móvel, por meio de violência contra uma pessoa, de ameaça com perigo iminente para a vida ou para a integridade física, ou pondo-a na impossibilidade de resistir, é punido com pena de prisão de 2 a 8 anos.',
    4
),
(
    243,
    'Homicídio',
    'Quem matar outra pessoa é punido com pena de prisão de 8 a 16 anos.',
    4
),
(
    333,
    'Corrupção Passiva',
    'O funcionário que por si, ou por interposta pessoa, com o seu consentimento ou ratificação, solicitar ou aceitar, para si ou para terceiro, vantagem patrimonial ou não patrimonial, que não lhe seja devida, com o fim de praticar um acto ou omissão contrários aos deveres do cargo, é punido com pena de prisão de 2 a 8 anos.',
    4
);

-- Artigos da Lei de Terras
INSERT INTO artigo (numero, titulo, conteudo, legislacao_id) VALUES
(
    1,
    'Propriedade da Terra',
    'A terra é propriedade originária do Estado, que pode transmitir a sua propriedade ou conceder o seu uso e aproveitamento a pessoas singulares ou colectivas, públicas ou privadas, nos termos da presente lei e demais legislação aplicável.',
    5
),
(
    35,
    'Concessão de Terrenos',
    'A concessão de terrenos é o acto pelo qual o Estado transmite para pessoas singulares ou colectivas o direito de superfície ou o direito de uso e aproveitamento de terras do domínio privado do Estado.',
    5
),
(
    58,
    'Direito de Ocupação',
    'São reconhecidos, como direitos de utilização das terras, os direitos de ocupação por pessoas singulares ou famílias que as cultivem ou habitem, desde que tal ocupação seja anterior a 1975.',
    5
);

-- Artigos do Código da Família
INSERT INTO artigo (numero, titulo, conteudo, legislacao_id) VALUES
(
    5,
    'Casamento',
    'O casamento é o vínculo jurídico estabelecido voluntariamente entre um homem e uma mulher com o propósito de constituírem família, mediante comunhão plena de vida.',
    6
),
(
    48,
    'Divórcio por Mútuo Consentimento',
    'O divórcio pode ser requerido, por mútuo consentimento, por ambos os cônjuges, desde que o casamento tenha pelo menos dois anos de duração. O divórcio por mútuo consentimento é decretado pelo tribunal.',
    6
),
(
    99,
    'Obrigação de Alimentos',
    'Os pais são obrigados a alimentar, educar e instruir os filhos. A obrigação de alimentos entre cônjuges e parentes existe na medida em que o titular não possa prover à sua subsistência.',
    6
),
(
    180,
    'Herança',
    'A herança compreende todos os bens, direitos e obrigações que não sejam intransmissíveis por morte. São herdeiros legítimos, pela ordem indicada: os descendentes, os ascendentes, o cônjuge sobrevivo e os colaterais.',
    6
);
