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

## Health Check

```bash
# Check gateway health
GET /api/health
```

## Example Usage

```bash
# Register
curl -X POST http://localhost:8080/api/register \
  -H "Content-Type: application/json" \
  -d '{"username":"john","password":"secret123","email":"john@example.com"}'

# Login
curl -X POST http://localhost:8080/api/login \
  -H "Content-Type: application/json" \
  -d '{"username":"john","password":"secret123"}'

# Get ingredients
curl http://localhost:8080/api/users/1/ingredients

# Add ingredient
curl -X POST http://localhost:8080/api/users/1/ingredients \
  -H "Content-Type: application/json" \
  -d '{"ingredientId":2,"quantity":"5 cloves"}'

# Update quantity
curl -X PATCH http://localhost:8080/api/users/1/ingredients/7 \
  -H "Content-Type: application/json" \
  -d '{"quantity":"10 cloves"}'

# Delete ingredient
curl -X DELETE http://localhost:8080/api/users/1/ingredients/7
```

## Notes
- Username must be 3-50 characters
- Password must be at least 6 characters
- `userIngredientId` is the ID from the user_ingredients table (returned in GET response)
