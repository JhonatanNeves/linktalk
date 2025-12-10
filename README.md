# 💬 LinkTalk

> Uma aplicação de chat moderna, desenvolvida do zero com as melhores práticas de Engenharia de Software Mobile.

O **LinkTalk** é uma aplicação de mensagens em tempo real que simula um ambiente profissional de chat. O projeto foi construído para demonstrar proficiência em **Android Nativo Moderno**, utilizando arquitetura escalável e tecnologias de ponta como Jetpack Compose e WebSockets.

---

## 📱 Layout & Design

O layout foi desenhado com foco na experiência do usuário (UX/UI). Você pode visualizar o protótipo de alta fidelidade e o Design System no link abaixo:

[![Figma](https://img.shields.io/badge/Acessar_Layout-Figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white)](https://www.figma.com/design/39yq2GG79kH2MFSRVnlhrb/LinkTalk-Project?t=dwGhGdt52pQXon4Z-0)

<div align="center">
  <img width="100%" alt="Preview do LinkTalk" src="https://github.com/user-attachments/assets/f98e4d8b-78b4-4934-813d-39f46f2646ef" />
</div>

---

## ✨ Funcionalidades

O aplicativo gerencia contatos e conversas via back-end, permitindo a criação de perfil e interação via chat.

- [x] **Autenticação:** Tela de Login (Email e Senha);
- [ ] **Social Login:** Integração com Google e Facebook;
- [x] **UI:** Tela de Splash personalizada;
- [ ] **Perfil:** Edição e visualização de perfil do usuário;
- [x] **Contatos:** Listagem e gerenciamento;
- [ ] **Home:** Lista de chats recentes com prévia da última mensagem;
- [ ] **Mensageria:** Chat individual em tempo real (WebSocket).

---

## 🛠 Tecnologias e Arquitetura

O projeto segue os princípios de **Clean Architecture** e **MVVM**, garantindo desacoplamento e testabilidade.

* **Linguagem:** Kotlin 100%
* **Interface (UI):** Jetpack Compose (Material Design 3)
* **Injeção de Dependência:** Dagger Hilt
* **Networking:** Ktor (REST & WebSockets)
* **Persistência:** Room & DataStore
* **Concorrência:** Coroutines & Flow
* **BaaS:** Firebase (Auth, Firestore, Storage, Cloud Messaging)

---

## 🚀 Como executar o projeto

### Pré-requisitos
* Android Studio Flamingo ou superior
* Java 11+ / Kotlin
* Dispositivo ou Emulador Android

### Passo a Passo

1. **Clone o repositório:**
```bash
git clone [https://github.com/JhonatanNeves/linktalk.git](https://github.com/JhonatanNeves/linktalk.git)