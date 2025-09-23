# Branch Protection Setup Guide

## 🔒 Setting up Branch Protection for Main Branch

To enforce API testing before merging to main branch, follow these steps:

### 1. Navigate to Repository Settings
- Go to your GitHub repository
- Click on **Settings** tab
- Select **Branches** from the left sidebar

### 2. Add Branch Protection Rule
- Click **Add rule** button
- Enter `main` as the branch name pattern

### 3. Configure Protection Settings

#### Required Status Checks:
✅ **Enable:** "Require status checks to pass before merging"
✅ **Enable:** "Require branches to be up to date before merging"

**Required status checks to add:**
- `Run API Tests`
- `Code Quality Checks`
- `api-tests`
- `code-quality`

#### Additional Protection:
✅ **Enable:** "Require pull request reviews before merging"
- Minimum: 1 review required
✅ **Enable:** "Dismiss stale PR approvals when new commits are pushed"
✅ **Enable:** "Require review from code owners" (optional)

#### Restrictions:
✅ **Enable:** "Restrict pushes that create files to this branch"
✅ **Enable:** "Do not allow bypassing the above settings"
❌ **Disable:** "Allow force pushes"
❌ **Disable:** "Allow deletions"

### 4. Administrative Settings
- **Include administrators:** Choose based on your needs
- **Allow force pushes:** ❌ Disabled
- **Allow deletions:** ❌ Disabled

## 🚀 GitHub Actions Workflows Created

### 1. `api-testing.yml`
**Trigger:** Pull requests to main branch
**Purpose:** Run API tests with @api and @regression tags
**Status:** Required for merge

### 2. `branch-protection.yml`
**Trigger:** Pull requests to main branch
**Purpose:** Code quality checks and project validation
**Status:** Required for merge

### 3. `complete-test-suite.yml`
**Trigger:** Schedule (daily) + Manual dispatch
**Purpose:** Comprehensive testing including all test types
**Status:** Informational

## 📋 Required Checks for Merge

Before any PR can be merged to main:

1. ✅ **API Tests** must pass (`@api` tag)
2. ✅ **API Regression Tests** must pass (`@api and @regression`)
3. ✅ **Code Quality Checks** must pass
4. ✅ **At least 1 review** required
5. ✅ **Branch must be up-to-date** with main
6. ✅ **Discord notifications** sent automatically

## 🔧 Workflow Features

### API Testing Workflow:
- Runs on Java 17
- Uses Maven for dependency management
- Executes API tests with RestAssured
- Generates comprehensive reports
- Comments PR with test results
- Uploads artifacts for analysis

### Branch Protection Workflow:
- Validates project structure
- Checks for required files
- Validates dependencies
- Ensures API feature files exist
- Confirms proper tag usage

### Complete Test Suite:
- Scheduled daily execution
- Manual trigger with test type selection
- Comprehensive reporting
- Artifact storage for analysis
- Discord notifications with detailed results

## 🎯 Benefits

1. **Quality Assurance:** No broken API code reaches main
2. **Automation:** Tests run automatically on PR
3. **Visibility:** Clear test results in PR comments
4. **Compliance:** Enforced through branch protection
5. **Reporting:** Detailed test reports and artifacts
6. **Real-time Notifications:** Discord alerts for all pipeline results
7. **Fast Execution:** API tests run in ~4 seconds (no device needed)

## 📱 Usage Examples

### Running specific tests locally:
```bash
# API tests only
mvn test -Dtest=TestRunner -Dcucumber.filter.tags="@api"

# API regression tests
mvn test -Dtest=TestRunner -Dcucumber.filter.tags="@api and @regression"

# All API smoke tests
mvn test -Dtest=TestRunner -Dcucumber.filter.tags="@api and @smoke"
```

### Manual workflow dispatch:
- Go to Actions tab
- Select "Complete Test Suite"
- Click "Run workflow"
- Choose test type: all, api, visual, smoke, regression

## ⚠️ Important Notes

1. **Mobile UI tests** are excluded from CI/CD (require physical device)
2. **API tests only** run in GitHub Actions (no Appium dependency)
3. **Visual tests** with Percy can run but require Percy token setup
4. **Local development** can run all test types including UI

## 🔄 Commit and Push

After setting up branch protection:
1. Commit these workflow files
2. Push to a feature branch
3. Create PR to main
4. Observe the workflows in action
5. Merge only after all checks pass

Your repository is now protected and requires API tests to pass before any merge to main! 🚀