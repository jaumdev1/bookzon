# BookZon
O projeto Bookzon é uma plataforma dedicada à discussão e exploração de livros de tecnologia. Combinando elementos de comunidade e educação, o Bookzon permite que os usuários descubram, discutam e compartilhem insights sobre uma variedade de obras literárias relacionadas à tecnologia. Os participantes podem explorar diferentes livros, iniciar e participar de discussões estruturadas, trocar comentários e insights, além de receber alertas para se manterem atualizados sobre novas interações e tópicos de interesse. A plataforma também oferece recursos para moderação e personalização de experiência, garantindo uma interação dinâmica e enriquecedora entre os entusiastas de tecnologia e literatura.


# OBS

Existe um acoplamento nesse projeto referente ao Framework do JPA na minha camada de domínio, eu tenho conhecimento que isso não é uma boa pratica para softwares que mudam frequentemente, e no futuro irei migrar os acoplamentos para camada de infra, e criando portas e adaptadores ( Arquitetura Hexagonal) para tal.


# Integração Google

A integração com google deve ser feita com baixo acoplamento, para que quando eu precisar trocar o serviço de consumo de livro seja baixo o aclopamento.