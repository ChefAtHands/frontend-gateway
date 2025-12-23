# Frontend Gateway
API Gateway for ChefAtHands - Port 8080

## Authentication Endpoints

```bash
# Register new user
POST /api/register
Body: {"username":"string","password":"string","email":"string"}

# Login
POST /api/login
Body: {"username":"string","password":"string"}
```

---

## Ingredient Endpoints

```bash
# Get all ingredients
GET /api/ingredients

# Get ingredient by ID
GET /api/ingredients/{id}

# Search ingredients by name
GET /api/ingredients/search?name={name}

# Create new ingredient
POST /api/ingredients
Body: {"name":"string","category":"string"}

# Delete ingredient
DELETE /api/ingredients/{id}
```

---

## User Ingredients Endpoints

```bash
# Get all ingredients for a user
GET /api/users/{userId}/ingredients

# Add ingredient to user
POST /api/users/{userId}/ingredients
Body: {"ingredientId":1,"quantity":"200g"}

# Update ingredient quantity
PATCH /api/users/{userId}/ingredients/{userIngredientId}
Body: {"quantity":"500g"}

# Remove ingredient from user
DELETE /api/users/{userId}/ingredients/{userIngredientId}
```

---

## Recommendation Endpoints

```bash
# Get recommendations for user
GET /api/recommendations?userId={userId}&category={category}&minProtein={minProtein}

# Get custom recommendations
POST /api/recommendations
Body: {
  "userId": 1,
  "ingredients": [{"name":"chicken"},{"name":"rice"}],
  "category": "Breakfast",
  "minProtein": 20
}
```

---

## Favourites Endpoints

```bash
# Get user's favourites
GET /api/users/{userId}/favourites

# Add recipe to favourites
POST /api/users/{userId}/favourites
Body: {"recipeId":"665261","notes":"Delicious!"}

# Check if recipe is favourite
GET /api/users/{userId}/favourites/{recipeId}/exists

# Remove from favourites
DELETE /api/users/{userId}/favourites/{recipeId}
```

---

## History Endpoints

```bash
# Get user's history
GET /api/users/{userId}/history

# Add recipe to history
POST /api/users/{userId}/history
Body: {"recipeId":"665261","viewedAt":"2025-12-23T12:00:00"}

# Check if recipe in history
GET /api/users/{userId}/history/{recipeId}/exists

# Remove from history
DELETE /api/users/{userId}/history/{recipeId}
```

---

## Notification Endpoints

```bash
# Get user's notifications
GET /api/users/{userId}/notifications

# Get unread notifications only
GET /api/users/{userId}/notifications?unreadOnly=true

# Get unread count
GET /api/users/{userId}/notifications/unread-count

# Create notification
POST /api/notifications
Body: {"userId":"1","message":"New recipe!","type":"recommendation"}

# Mark notification as read
POST /api/users/{userId}/notifications/{notificationId}/read

# Mark all as read
POST /api/users/{userId}/notifications/read-all

# Delete notification
DELETE /api/users/{userId}/notifications/{notificationId}
```

---

## Logging Endpoints

```bash
# Get all logs
GET /api/logs

# Get logs by level (INFO, WARN, ERROR)
GET /api/logs/level/{level}

# Create a log entry
POST /api/logs
Body: {"level":"INFO","message":"string"}

# Log INFO message
POST /api/logs/info
Body: "This is an info message"

# Log WARN message
POST /api/logs/warn
Body: "This is a warning message"

# Log ERROR message
POST /api/logs/error
Body: "This is an error message"
```

---

## Health Check

```bash
# Check gateway health
GET /api/health
```

---

## Example Usage

### Authentication

```bash
# Register
curl -X POST http://localhost:8080/api/register \
  -H "Content-Type: application/json" \
  -d '{"username":"john","password":"secret123","email":"john@example.com"}'

# Login
curl -X POST http://localhost:8080/api/login \
  -H "Content-Type: application/json" \
  -d '{"username":"john","password":"secret123"}'
```

---

### Ingredient Catalog

```bash
# Get all ingredients
curl http://localhost:8080/api/ingredients

# Search for "chicken"
curl "http://localhost:8080/api/ingredients/search?name=chicken"

# Get specific ingredient
curl http://localhost:8080/api/ingredients/3

# Create new ingredient
curl -X POST http://localhost:8080/api/ingredients \
  -H "Content-Type: application/json" \
  -d '{"name":"Avocado","category":"Fruit"}'

# Delete ingredient
curl -X DELETE http://localhost:8080/api/ingredients/25
```

---

### Frontend Flow: Adding Ingredients to User Pantry

```bash
# 1. User types "tom" in search box
curl "http://localhost:8080/api/ingredients/search?name=tom"
# Returns: [{"id":1,"name":"Tomato","category":"Vegetable"}]

# 2. User selects "Tomato" and enters quantity "3 pieces"
curl -X POST http://localhost:8080/api/users/1/ingredients \
  -H "Content-Type: application/json" \
  -d '{"ingredientId":1,"quantity":"3 pieces"}'
# Returns: {"id":14,"userId":1,"ingredientId":1,"quantity":"3 pieces"}

# 3. User views their pantry
curl http://localhost:8080/api/users/1/ingredients

# 4. User updates quantity to "5 pieces"
curl -X PATCH http://localhost:8080/api/users/1/ingredients/14 \
  -H "Content-Type: application/json" \
  -d '{"quantity":"5 pieces"}'

# 5. User removes ingredient from pantry
curl -X DELETE http://localhost:8080/api/users/1/ingredients/14
```

---

### User Ingredients

```bash
# Get user's ingredients
curl http://localhost:8080/api/users/1/ingredients

# Add ingredient to user's pantry
curl -X POST http://localhost:8080/api/users/1/ingredients \
  -H "Content-Type: application/json" \
  -d '{"ingredientId":2,"quantity":"5 cloves"}'

# Update ingredient quantity
curl -X PATCH http://localhost:8080/api/users/1/ingredients/7 \
  -H "Content-Type: application/json" \
  -d '{"quantity":"10 cloves"}'

# Remove ingredient from pantry
curl -X DELETE http://localhost:8080/api/users/1/ingredients/7
```

---

### Recommendations

```bash
# Get recommendations for user
curl "http://localhost:8080/api/recommendations?userId=1&category=Breakfast&minProtein=20"

# Get custom recommendations
curl -X POST http://localhost:8080/api/recommendations \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 1,
    "ingredients": [{"name":"chicken"},{"name":"rice"}],
    "category": "Breakfast",
    "minProtein": 20
  }'
```

---

### Favourites

```bash
# Get user's favourites
curl http://localhost:8080/api/users/1/favourites

# Add to favourites
curl -X POST http://localhost:8080/api/users/1/favourites \
  -H "Content-Type: application/json" \
  -d '{"recipeId":"665261","notes":"Loved it!"}'

# Check if favourite
curl http://localhost:8080/api/users/1/favourites/665261/exists

# Remove from favourites
curl -X DELETE http://localhost:8080/api/users/1/favourites/665261
```

---

### History

```bash
# Get user's history
curl http://localhost:8080/api/users/1/history

# Add to history
curl -X POST http://localhost:8080/api/users/1/history \
  -H "Content-Type: application/json" \
  -d '{"recipeId":"665261","viewedAt":"2025-12-23T12:00:00"}'

# Check if in history
curl http://localhost:8080/api/users/1/history/665261/exists

# Remove from history
curl -X DELETE http://localhost:8080/api/users/1/history/665261
```

---

### Notifications

```bash
# Get all notifications
curl http://localhost:8080/api/users/1/notifications

# Get unread only
curl "http://localhost:8080/api/users/1/notifications?unreadOnly=true"

# Get unread count
curl http://localhost:8080/api/users/1/notifications/unread-count

# Create notification
curl -X POST http://localhost:8080/api/notifications \
  -H "Content-Type: application/json" \
  -d '{"userId":"1","message":"New recipes available!","type":"recommendation"}'

# Mark as read
curl -X POST http://localhost:8080/api/users/1/notifications/123/read

# Mark all as read
curl -X POST http://localhost:8080/api/users/1/notifications/read-all

# Delete notification
curl -X DELETE http://localhost:8080/api/users/1/notifications/123
```

---

### Logging

```bash
# Get all logs
curl http://localhost:8080/api/logs

# Get INFO logs
curl http://localhost:8080/api/logs/level/INFO

# Get ERROR logs
curl http://localhost:8080/api/logs/level/ERROR

# Create log
curl -X POST http://localhost:8080/api/logs \
  -H "Content-Type: application/json" \
  -d '{"level":"INFO","message":"Test log"}'

# Log info
curl -X POST http://localhost:8080/api/logs/info \
  -H "Content-Type: application/json" \
  -d '"User performed action"'

# Log warning
curl -X POST http://localhost:8080/api/logs/warn \
  -H "Content-Type: application/json" \
  -d '"Low memory warning"'

# Log error
curl -X POST http://localhost:8080/api/logs/error \
  -H "Content-Type: application/json" \
  -d '"Database connection failed"'
```

---

### Health Check

```bash
# Check health
curl http://localhost:8080/api/health
```

---

## Validation Rules

- **Username**: 3-50 characters
- **Password**: Minimum 6 characters
- **Email**: Valid email format
- **Ingredient Name**: Required for ingredient creation
- **Quantity**: Required string (e.g., "200g", "5 pieces", "1 cup")
- **userIngredientId**: ID from user_ingredients table (returned in GET response)

---

## Backend Services

The gateway proxies requests to the following services:

- **User Service** (Port 8083): Authentication and user management
- **Ingredient Service** (Port 8081): Ingredient catalog and user ingredient management
- **Recommendation Service** (Port 8082): Recipe recommendations
- **Recipe Search Service** (Port 8085): Recipe search with Spoonacular
- **Favourites Service** (Port 8086): User's favorite recipes
- **History Service** (Port 8088): Recipe viewing history
- **Notification Service** (Port 8087): User notifications
- **Logging Service** (Port 8084): Application logging

---

## Running the Gateway

```bash
cd frontend-gateway
mvn clean install
mvn spring-boot:run
```

Gateway will start on **http://localhost:8080**

---

## Running All Services

```bash
# Terminal 1: User Service
cd core-services/user-service
mvn spring-boot:run

# Terminal 2: Ingredient Service
cd core-services/ingredient-service
mvn spring-boot:run

# Terminal 3: Recommendation Service
cd processing-services/recommendation-service
mvn spring-boot:run

# Terminal 4: Recipe Search Service
cd external-api-services/recipe-search-service
mvn spring-boot:run

# Terminal 5: Favourites Service
cd utility-services/favourites-service
mvn spring-boot:run

# Terminal 6: History Service
cd utility-services/history-service
mvn spring-boot:run

# Terminal 7: Notification Service
cd utility-services/notification-service
mvn spring-boot:run

# Terminal 8: Logging Service
cd utility-services/logging-service
mvn spring-boot:run

# Terminal 9: Frontend Gateway
cd frontend-gateway
mvn spring-boot:run
```

---

## Configuration

Edit `src/main/resources/application.properties`:

```properties
server.port=8080

services.user.url=http://localhost:8083
services.ingredient.url=http://localhost:8081
services.recommendation.url=http://localhost:8082
services.recipe-search.url=http://localhost:8085
favourites.service.url=http://localhost:8086
notification.service.url=http://localhost:8087
history.service.url=http://localhost:8088
```

---

## Architecture

```
Frontend
   ↓
Frontend Gateway (8080)
   ↓
   ├── User Service (8083)
   ├── Ingredient Service (8081)
   ├── Recommendation Service (8082)
   ├── Recipe Search Service (8085)
   ├── Favourites Service (8086)
   ├── History Service (8088)
   ├── Notification Service (8087)
   └── Logging Service (8084)
```

---

## Notes

- All requests go through the Gateway (port 8080)
- Backend services should not be accessed directly from frontend
- Logging service failures will not break main functionality
- PATCH requests require Apache HttpClient dependency (already configured)
- Favourites, History, and Notifications are currently mock services