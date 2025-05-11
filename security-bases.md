# 📘 Documentation – Authentification avec Keycloak

---

## 🔐 Partie 1 : JWT (JSON Web Token)

### 📌 Qu’est-ce qu’un JWT ?

Le **JSON Web Token** est un format standardisé (RFC 7519) qui permet d’échanger des informations (*claims*) entre deux entités de manière sécurisée.

### 🧱 Structure d’un JWT

Un JWT est une chaîne encodée en **Base64**, composée de **trois parties séparées par des points** :

```plaintext
eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvZSIsImlhdCI6MTUxNjIzOTAyMn0.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
   |           HEADER           |         PAYLOAD         |          SIGNATURE
```

* **Header** : type de token et algorithme de signature

```json
{
  "alg": "RS256",
  "typ": "JWT"
}
```

* **Payload (claims)** : données non chiffrées

```json
{
  "sub": "1234567890",
  "name": "John Doe",
  "iat": 1516239022,
  "exp": 1516242622,
  "roles": ["admin", "user"]
}
```

* **Signature** : signature cryptographique sur header + payload

### ✅ Avantages

* Self-contained
* Portable
* Idéal pour authentification stateless

### ⚠️ Attention

* Données visibles (non chiffrées)
* Utiliser HTTPS impérativement
* Ne pas inclure d’infos sensibles dans le payload

---

## ⚖️ Partie 2 : Authentification Stateful vs Stateless

### 🧭 Stateful Authentication

#### 🔁 Fonctionnement

* Le serveur **stocke une session utilisateur** (ID + data en mémoire ou base)
* Le client reçoit un cookie avec l’ID de session

#### ✅ Avantages

* Révocation facile
* Compatible avec les monolithes

#### ❌ Inconvénients

* Moins scalable
* Complexité dans les environnements distribués

---

### 🛰️ Stateless Authentication

#### 🔁 Fonctionnement

* Le serveur **ne garde aucun état**
* Le client envoie un JWT dans l’en-tête HTTP `Authorization: Bearer <token>`

#### ✅ Avantages

* Très scalable
* Parfait pour les API REST/microservices
* Pas besoin de session store

#### ❌ Inconvénients

* Révocation difficile
* Gestion du renouvellement nécessaire

---

## 🔁 Partie 3 : OAuth2 et OpenID Connect avec Keycloak

### 🔑 1. Rappel sur OAuth2

#### 🎯 Objectif

Permettre à une application (*client*) d’accéder à une ressource au nom de l’utilisateur, sans connaître ses identifiants.

#### 📄 Terminologie

* **Resource Owner** : utilisateur final
* **Client** : application
* **Authorization Server** : Keycloak
* **Resource Server** : API protégée

---

### 🧩 2. Extension avec OpenID Connect (OIDC)

* Ajoute l’**authentification** (ID Token)
* Fournit des **informations utilisateur** (`sub`, `email`, etc.)

---

### 🔁 3. Authorization Code Flow (flow recommandé)

1. L’utilisateur est redirigé vers Keycloak
2. Il s’authentifie
3. Redirection vers le client avec un **code temporaire**
4. Le client échange ce code contre :

   * un **access token** (JWT)
   * un **ID token** (JWT)
   * un **refresh token**

---

### 🛡️ 4. Sécurité avec Keycloak

* Signature des tokens avec une **clé privée** (RS256)
* Vérification via une **clé publique exposée en JWKS** : `https://<keycloak>/realms/<realm>/protocol/openid-connect/certs`
* Authentification stateless possible : validation sans contact avec le serveur

---

### 📌 5. Exemples d’usage

* **Frontend** (React/Vue) : OIDC pour login
* **Backend/API** : vérifie le JWT (Keycloak Adapter ou lib JWT)
* **Rôles/Permissions** : intégrés dans les claims du token

---

> 🧠 Astuce : toujours tester avec [jwt.io](https://jwt.io) pour visualiser un JWT

-
