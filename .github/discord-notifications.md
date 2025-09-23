# Discord Notifications Setup

## 🔔 Automated Notifications

Workflow ini akan mengirim notifikasi otomatis ke Discord channel untuk setiap hasil pipeline.

## 📋 Notification Types

### 1. **API Testing CI/CD**

**Trigger:** Pull requests to main branch
**Content:**

- ✅/❌ Overall status (PASSED/FAILED)
- Repository information
- Branch name
- Event type (pull_request/push)
- Tests executed (@api, @api and @regression)
- Commit link
- Actor (who triggered)
- Timestamp

## 🎨 Message Format

### Success Message (Green):

```json
{
  "title": "✅ API Testing CI/CD - PASSED",
  "description": "All API tests passed successfully! Ready for merge 🚀",
  "color": 3066993,
  "fields": [
    {
      "name": "Repository",
      "value": "your-repo/name",
      "inline": true
    },
    {
      "name": "Branch",
      "value": "feature/branch-name",
      "inline": true
    },
    {
      "name": "Tests Run",
      "value": "@api tag tests\n@api and @regression tests",
      "inline": false
    }
  ]
}
```

### Failure Message (Red):

```json
{
  "title": "❌ API Testing CI/CD - FAILED",
  "description": "API tests failed. Please fix the issues before merging.",
  "color": 15158332
}
```

## 🔗 Webhook URL

Discord webhook sudah dikonfigurasi di workflow files:

```
https://discord.com/api/webhooks/1418989799444054036/EaHp_OVJAnpziDcfqdhyyBEZ8Bnuhu4e8ez-BHbdLy5pBMavIjqZ3pTIM12NHlMm0JjW
```

## 🎯 Benefits

1. **Real-time Updates:** Get notified immediately when tests complete
2. **Rich Information:** Detailed test results with links and metadata
3. **Visual Status:** Color-coded messages (green=success, red=failure)
4. **Team Collaboration:** Everyone in Discord gets the same information
5. **Historical Record:** All notifications are preserved in Discord

## 📱 Example Notifications

### Pull Request Success:

> ✅ **API Testing CI/CD - PASSED**
>
> All API tests passed successfully! Ready for merge 🚀
>
> **Repository:** your-repo/selenium-java-appium-halodoc
> **Branch:** feature/new-api-endpoint
> **Event:** pull_request
> **Tests Run:** @api tag tests, @api and @regression tests
> **Actor:** developer-name

### Daily Test Suite:

> ✅ **Complete Test Suite - COMPLETED**
>
> Complete test suite finished successfully! 🎉
>
> **API Tests:** ✅ PASSED
> **Smoke Tests:** ✅ PASSED
> **Regression Tests:** ✅ PASSED
> **Trigger:** schedule
> **Artifacts:** 📊 Test reports available in GitHub Actions

## ⚙️ Customization

To modify notifications, edit the webhook URLs in:

- `.github/workflows/api-testing.yml`
- `.github/workflows/complete-test-suite.yml`

The notification format can be customized by modifying the JSON payload in the `curl` commands within the workflow files.
