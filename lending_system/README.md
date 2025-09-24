


#  Lending System — Object-Oriented Analysis & Design (1DV607)

**Course:** 1DV607 – Object Oriented Analysis and Design using UML, 7.5 credits  
**Institution:** Linnaeus University – Faculty of Technology  
**Module/Folder:** `lending_system`  
**Author(s):** * Shaimaa Bakir
* Marah Awad
* Mustafa Habeb
**Term:** [Add term/year]  

> This repository documents a complete OO analysis & design of a **Lending System** (e.g., library/video/tool lending). It focuses on **requirements, UML modeling, and design-to-implementation mapping**, aligned with 1DV607 learning outcomes.

---

## Table of Contents
- [Overview](#overview)  
- [Problem Statement & Scope](#problem-statement--scope)  
- [Stakeholders & Assumptions](#stakeholders--assumptions)  
- [Requirements](#requirements)  
  - [Functional](#functional)  
  - [Non‑Functional](#non-functional)  
- [UML Models](#uml-models)  
  - [Use Case Model](#use-case-model)  
  - [Domain Model (Class Diagram)](#domain-model-class-diagram)  
  - [Interaction (Sequence Diagrams)](#interaction-sequence-diagrams)  
  - [State Machine(s)](#state-machines)  
- [Design](#design)  
  - [Architecture](#architecture)  
  - [Key Classes](#key-classes)  
  - [Design Patterns](#design-patterns)  
- [Data Model](#data-model)  
- [From Design to Code](#from-design-to-code)  
- [How to View/Run](#how-to-viewrun)  
- [Testing Approach](#testing-approach)  
- [Folder Structure](#folder-structure)  
- [Course Alignment (1DV607)](#course-alignment-1dv607)  
- [Future Improvements](#future-improvements)  
- [License](#license)

---

## Overview
The **Lending System** manages **items** (e.g., books), **members**, and **loans**. It supports searching the catalog, borrowing, returning, renewals, reservations/queues, fines, and notifications. The project demonstrates the full OO process: **requirements → analysis models → design → code skeleton/tests**.

---

## Problem Statement & Scope
An organization needs a system that enables members to **borrow items** for a limited time while tracking **availability, due dates, penalties**, and **reservations**.  
**In scope:** member management, catalog search, borrowing/returning, renewals, reservation queue, simple fines, staff administration.  
**Out of scope:** online payments, complex identity management, external integrations (unless noted).

---

## Stakeholders & Assumptions
- **Actors:** Member, Librarian/Staff, System Admin, Notification Service.  
- **Assumptions:** a unique **ItemCopy** per physical item; fines are flat per day; reservations are FIFO; items may have different **LoanPolicies** (reference-only, 7/14/28 days).

---

## Requirements

### Functional
1. **Register Member** and update contact details.  
2. **Search Catalog** by title, author, tag, or ISBN.  
3. **Borrow Item** if a copy is available; create **Loan** with due date.  
4. **Return Item**; update availability and compute **fine** if overdue.  
5. **Renew Loan** if no active reservation exists.  
6. **Reserve Item**; maintain a **queue** and notify next member when available.  
7. **Administer Inventory** (add/remove items and copies).  
8. **Administrative Reports**: overdue loans, popular items, active reservations.

### Non‑Functional
- **Usability:** clear workflows for staff; simple error messages.  
- **Reliability:** no lost loans/reservations; ACID persistence if implemented.  
- **Performance:** search returns in < 1s for typical data sets.  
- **Security:** basic role separation (staff vs member).  
- **Maintainability:** layered architecture; cohesive classes; documented models.  

---

## UML Models

### Use Case Model
Key use cases: **Register Member, Search Catalog, Borrow Item, Return Item, Renew Loan, Reserve Item, Manage Inventory, View Reports.**  
Include/update the diagram file(s) here:  
- `uml/use-cases/lending-use-cases.puml` *(PlantUML)* or `.drawio`/`.png`

### Domain Model (Class Diagram)
Core entities and relationships:
- **Member** 1..* **Loan** * * **ItemCopy** → **Item** (Book, DVD, Tool via inheritance)  
- **Reservation** (Member ↔ Item) with queue order  
- **Fine** calculated from **Loan** overdues  
- **LoanPolicy** strategy per **Item** type

Place your class diagram at: `uml/domain/lending-domain-class.puml` (or image).

### Interaction (Sequence Diagrams)
Recommended sequences:
- `Borrow Item` — *search → check availability → create Loan → update ItemCopy → print slip/notify*  
- `Return Item` — *locate Loan → compute fine → close Loan → release to next in reservation queue*  
- `Reserve Item` — *enqueue Member → notify when available → convert to Loan*

### State Machines
- **Loan** states: *Active → Overdue → Closed*  
- **ItemCopy** states: *Available → OnLoan → Reserved → Lost*  

---

## Design

### Architecture
Layered design:
- **UI** (console/GUI/Web)  
- **Application/Service** (use‑case orchestration)  
- **Domain** (entities, value objects, policies)  
- **Infrastructure** (repositories, persistence, notifications)

### Key Classes
- `Member`, `Item`, `ItemCopy`, `Loan`, `Reservation`, `Fine`, `LoanPolicy`  
- Services: `CirculationService`, `ReservationService`, `CatalogService`  
- Repos: `MemberRepository`, `ItemRepository`, `LoanRepository`, `ReservationRepository`

### Design Patterns
- **Strategy** — `LoanPolicy` per item type (loan duration, renew rules).  
- **State** — `Loan` life‑cycle; `ItemCopy` availability.  
- **Factory** — create `Item`/`ItemCopy` variants.  
- **Repository** — persistence abstraction for aggregates.  
- **Observer/Publisher** — notify member when a reserved item becomes available.

---

## Data Model
If a relational DB is used, suggested tables: `members, items, item_copies, loans, reservations, fines`.  
If in‑memory, document storage layout and sample data in `data/` (JSON/CSV).

---

## From Design to Code
- Each **use case** maps to a **service method** (`CirculationService.borrow(...)`, `return(...)`, etc.).  
- **Repositories** hide storage; **domain** classes remain persistence‑agnostic.  
- **DTOs** or **mappers** for UI ↔ domain transitions.  
- Start with **unit tests** for domain rules (e.g., overdue fine calculation).

---

## How to View/Run

### View diagrams
Open files in `uml/` with **PlantUML**, **Draw.io**, or any UML viewer.  
If using PlantUML locally:
```bash
# Example: render all .puml diagrams to PNG (requires Java + PlantUML jar)
plantuml -tpng uml/**/*.puml
```

### Run code (if implementation is included)
```bash
# Java & Gradle example
./gradlew test        # run unit tests
./gradlew run         # launch console/GUI (configure main class)
```

---

## Testing Approach
- **Unit tests** for domain rules (loan duration, fines, reservation eligibility).  
- **Integration tests** for repository persistence (in‑memory or DB).  
- **Scenario tests** for complete flows (borrow → return with fine).

---

## Folder Structure
```
lending_system/
├─ docs/
│  ├─ vision.md
│  ├─ requirements.md
│  └─ glossary.md
├─ uml/
│  ├─ use-cases/
│  ├─ domain/
│  ├─ sequences/
│  └─ states/
├─ src/                 # optional: code (Java/…)
│  ├─ main/
│  └─ test/
├─ data/                # sample CSV/JSON
└─ README.md
```

---

## Course Alignment (1DV607)
- **Analysis & understanding:** concepts/principles of OO analysis and design; modeling with UML.  
- **Application:** building UML models and **transforming designs into implementation** and vice‑versa.  
- **Evaluation:** reflect on design choices, patterns, and provide **constructive critique** on peers’ models.

*(This wording mirrors the intent of the official 1DV607 course plan.)*

---

## Future Improvements
- Fees configuration & payment gateway integration.  
- Role‑based access control and audit logs.  
- Analytics dashboards (popular items, utilization, trends).  
- Email/SMS notification adapters.  
- Import/export catalog data.

---

## License
Academic use for **1DV607**. 
