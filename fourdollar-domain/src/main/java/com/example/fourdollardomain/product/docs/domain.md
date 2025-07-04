# 상품 도메인 모델

## 도메인 모델

---

### [상품 애그리거트]

### 상품(Product)

*Aggregate Root*

#### 속성

- `id`: `Long`
- `productCode`: 상품 코드 - Natural ID
- `name`: 상품명
- `slug`: 슬러그
- `shortDescription`: 짧은 설명
- `fullDescription`: 상세 설명
- `basePrice`: `BigDecimal` 기본 가격
- `status`: `ProductStatus` 상품 상태
- `optionGroups`: `List<ProductOptionGroup>` 1:N
- `categories`: `List<ProductCategory>` 1:N
- `createdAt`: 생성 일시
- `updatedAt`: 수정 일시

#### 행위

- `create()`: 상품 생성 - name, slug, description, price, sellerId, brandId
- `modify()`: 상품 정보 수정 - name, description, price (주문 없는 경우만)
- `activate()`: 판매 시작 (Waiting → OnSale)
- `suspend()`: 판매 일시 중단 (OnSale → Suspended)
- `resume()`: 판매 재개 (Suspended → OnSale)
- `delete()`: 상품 삭제 (소프트 삭제)
- `addOptionGroup()`: 옵션 그룹 추가
- `removeOptionGroup()`: 옵션 그룹 제거 (재고 없는 경우만)
- `checkStockStatus()`: 재고 상태 확인 후 상태 자동 변경

#### 규칙

- 상품 생성 후 상태는 Waiting
- 옵션 그룹을 1개 이상 가지고 있어야 판매중으로 변경 가능
- 대기 상태에서만 상품 옵션 정보를 변경할 수 있다.
- 대기, 일시중지 상태에서만 기본 정보를 변경할 수 있다.
- 종료되지 않은 상품만 카테고리를 변경할 수 있다.
- OnSale 상태에서만 고객에게 노출된다.
- 모든 SKU 재고가 0이면 자동으로 OutOfStock 상태 변경
- 재고가 다시 생기면 OnSale 상태로 변경할 수 있다.
- 최소 1개의 옵션 그룹 보유 필수
- 판매자는 자신의 상품만 관리 가능

### 상품 옵션 그룹(ProductOptionGroup)

*Entity*

#### 속성

- `id`: `Long`
- `productId`: `Long`
- `name`: 옵션 그룹명 (색상, 사이즈 등)
- `groupOrder`: 그룹 순서
- `displayOrder`: 표시 순서
- `options`: `List<ProductOption>` 1:N

#### 행위

- `static create()`: 옵션 그룹 생성 - productId, name, groupOrder
- `addOption()`: 옵션 추가
- `removeOption()`: 옵션 제거 (재고 없는 경우만)
- `changeOrder()`: 순서 변경 (주문 없는 경우만)

#### 규칙

- 같은 상품 내에서 groupOrder 중복 불가
- 같은 상품 내에서 그룹명 중복 불가
- 최소 1개의 옵션 보유 필수

### 상품 옵션(ProductOption)

*Entity*

#### 속성

- `id`: `Long`
- `optionGroupId`: `Long`
- `name`: 옵션명 (빨강, 파랑, S, M 등)
- `optionCode`: 옵션 코드 (01, 02, 03...)
- `additionalPrice`: `Money` 추가 금액
- `displayOrder`: 표시 순서

#### 행위

- `static create()`: 옵션 생성 - optionGroupId, name, optionCode, additionalPrice
- `modify()`: 옵션 수정 (주문 없는 경우만)
- `changePrice()`: 추가 금액 변경

#### 규칙

- 옵션 코드는 2자리 숫자 형식 ("01", "02", "99")
- 같은 그룹 내에서 optionCode 중복 불가
- "00"은 기본 옵션 전용 코드
- 추가 금액은 0 이상만 허용
- 주문이 있는 옵션의 핵심 정보(optionCode, name) 수정 불가

### 상품 상태(ProductStatus)

*Enum*

#### 상수

- `WAITING`: 대기
- `ON_SALE`: 판매중
- `SUSPENDED`: 일시중지
- `Discountinued`: 판매종료

#### 상태 전환 다이어그램

```
Waiting → OnSale ⟷ Discountinued
  ↓         ↓         ↓
Suspended ← Suspended ← Suspended
  ↑                   ↓
  └─── Suspended ←────┘
```

#### 상태별 권한 매트릭스

| 기능      | Waiting | OnSale | OutOfStock | Suspended | Deleted |
|---------|---------|--------|------------|-----------|---------|
| 고객 조회   | ❌       | ✅      | ✅ (품절표시)   | ❌         | ❌       |
| 장바구니 담기 | ❌       | ✅      | ❌          | ❌         | ❌       |
| 주문 가능   | ❌       | ✅      | ❌          | ❌         | ❌       |
| 정보 수정   | ✅       | 제한적*   | 제한적*       | ✅         | ❌       |
| 재고 관리   | ✅       | ✅      | ✅          | ✅         | ❌       |
| 상태 변경   | ✅       | ✅      | ✅          | ✅         | ❌       |

---

## Exceptions

### DuplicateSKUException

*Exception*

- SKU 코드 중복 시 발생

### InsufficientStockException

*Exception*

- 재고 부족 시 발생

### BusinessRuleViolationException

*Exception*

- 비즈니스 규칙 위반 시 발생

### UnauthorizedAccessException

*Exception*

- 권한 없는 접근 시 발생

### InvalidOptionCodeException

*Exception*

- 잘못된 옵션 코드 형식 시 발생

### InvalidPriceException

*Exception*

- 잘못된 가격 설정 시 발생

---

## Domain Services

### SKUGenerator

*Domain Service*

#### 행위

- `generateSKUCode()`: SKU 코드 생성
- `validateSKUFormat()`: SKU 형식 검증

### StockManager

*Domain Service*

#### 행위

- `checkStockAvailability()`: 재고 가용성 확인
- `reserveStock()`: 재고 예약
- `releaseStock()`: 재고 해제

### PriceCalculator

*Domain Service*

#### 행위

- `calculateFinalPrice()`: 최종 가격 계산
- `validatePriceConsistency()`: 가격 일관성 검증

