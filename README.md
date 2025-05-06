# linktalk

[FIGMA](https://www.figma.com/design/39yq2GG79kH2MFSRVnlhrb/LinkTalk-Project?t=dwGhGdt52pQXon4Z-0)

<img width="871" alt="Captura de Tela 2024-12-16 às 18 52 44" src="" />

## Como a aplicação funciona?
O aplicativo deve exibir uma lista de contatos e conversas administrados no back-end, podendo criar um perfil personalizado de usuário , podendo interarir com outros usuários atraves de chat.

## Funcionalidades?

- [x] Tela de Login (Com email e senha);
- [x] Login com conta existente (Google, Facebook, etc.);
- [x] Tela de splash personalizada;
- [x] Perfil do Usuário;
- [x] Contatos;
- [x] Conversas Lista de chats recentes com prévia da última mensagem;
- [x] Chat individual;

## 🛠 Principais Tecnologias utilizadas

Para o desenvolvimento desta aplicação utilizei as seguintes tecnologias:

- Jetpack Compose para UI;
- Componentes de Arquitetura;
- Injeção de dependência com Dagger Hilt;
- Tecnologia WebSocket;
- Ktor na camada de netwrok para requisições REST;
- Repository Pattern;

## 🚀 Pré-requisitos
- Android Studio Flamingo ou superior

- Java 11+ ou Kotlin
- Gradle 8.0+
- Conexão com Firebase (Auth, Firestore, Storage, Cloud Messaging)

## 🚀 Como testar a aplicação

```bash
# Clone o repositório
git clone https://github.com/JhonatanNeves/linktalk.git

# Abra no Android Studio

# Configure o Firebase:
Crie um projeto no Firebase Console
Baixe o arquivo google-services.json e coloque na pasta app/
Habilite:
- Authentication (Email/Password, Google, etc.)
- Firestore Database
- Storage
- Cloud Messaging (para notificações)

## Sincronize e execute o projeto:
Sincronize o Gradle
Rode em um dispositivo/emulador Android
```
---

#### O LinkTalk é uma aplicação de chat desenvolvido do absoluto zero, colocando em prática conhecimento voltado ao mercado, utilizando tecnologias modernas no desenvolvimento Anrdoid moderno.
