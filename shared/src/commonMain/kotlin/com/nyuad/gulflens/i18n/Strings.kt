package com.nyuad.gulflens.i18n

/** Reading direction metadata kept independent from Compose so it is usable on every target. */
enum class TextDirection {
    LeftToRight,
    RightToLeft,
}

/** Languages currently supported by GulfLens. */
enum class AppLanguage(
    val tag: String,
    val displayName: String,
    val textDirection: TextDirection,
) {
    English("en", "English", TextDirection.LeftToRight),
    SimplifiedChinese("zh-Hans", "中文", TextDirection.LeftToRight),
    Arabic("ar", "العربية", TextDirection.RightToLeft);

    val isRightToLeft: Boolean
        get() = textDirection == TextDirection.RightToLeft

    companion object {
        fun fromTag(tag: String?): AppLanguage {
            val normalized = tag?.trim()?.lowercase().orEmpty()
            return when {
                normalized == "zh" || normalized.startsWith("zh-") -> SimplifiedChinese
                normalized == "ar" || normalized.startsWith("ar-") -> Arabic
                else -> English
            }
        }
    }
}

/**
 * Stable identifiers for user-facing copy. Keep domain state and navigation independent
 * from translated text by storing these keys rather than rendered strings.
 */
enum class TextKey(
    internal val english: String,
    internal val simplifiedChinese: String,
    internal val arabic: String,
) {
    APP_NAME(
        english = "GulfLens",
        simplifiedChinese = "GulfLens",
        arabic = "GulfLens",
    ),
    LANGUAGE(
        english = "Language",
        simplifiedChinese = "语言",
        arabic = "اللغة",
    ),
    DARK_MODE(
        english = "Dark mode",
        simplifiedChinese = "深色模式",
        arabic = "الوضع الداكن",
    ),
    LIGHT_MODE(
        english = "Light mode",
        simplifiedChinese = "浅色模式",
        arabic = "الوضع الفاتح",
    ),
    BACK(
        english = "Back",
        simplifiedChinese = "返回",
        arabic = "رجوع",
    ),
    CLOSE(
        english = "Close",
        simplifiedChinese = "关闭",
        arabic = "إغلاق",
    ),
    RESTART(
        english = "Restart",
        simplifiedChinese = "重新开始",
        arabic = "إعادة البدء",
    ),
    EDIT_SCENARIO(
        english = "Edit scenario",
        simplifiedChinese = "编辑情景",
        arabic = "عدّل السيناريو",
    ),
    PROCESSING(
        english = "Processing",
        simplifiedChinese = "处理中",
        arabic = "جارٍ المعالجة",
    ),
    DUBAI(
        english = "Dubai",
        simplifiedChinese = "Dubai",
        arabic = "دبي",
    ),
    ABU_DHABI(
        english = "Abu Dhabi",
        simplifiedChinese = "Abu Dhabi",
        arabic = "أبوظبي",
    ),
    LANDING_INPUTS_A11Y(
        english = "Information the client provides",
        simplifiedChinese = "客户提供的信息",
        arabic = "المعلومات التي يقدّمها العميل",
    ),
    LANDING_INPUTS_LABEL(
        english = "Client shares",
        simplifiedChinese = "客户提供",
        arabic = "يشارك العميل",
    ),
    LANDING_INPUT_COMPANY_OR_STARTUP(
        english = "We’re a company or startup",
        simplifiedChinese = "我们是一家公司或初创企业",
        arabic = "نحن شركة قائمة أو ناشئة",
    ),
    LANDING_INPUT_BUSINESS_PROFILE(
        english = "Here’s our business profile",
        simplifiedChinese = "这是我们的业务概况",
        arabic = "هذا هو ملف أعمالنا",
    ),
    LANDING_INPUT_PRODUCT_OR_SERVICE(
        english = "This is our product or service",
        simplifiedChinese = "这是我们的产品或服务",
        arabic = "هذا هو منتجنا أو خدمتنا",
    ),
    LANDING_INPUT_OPERATING_SECTOR(
        english = "Our operating sector",
        simplifiedChinese = "我们的经营行业",
        arabic = "قطاع عملنا",
    ),
    LANDING_INPUT_ENTRY_MODE(
        english = "Our preferred entry mode",
        simplifiedChinese = "我们偏好的进入模式",
        arabic = "أسلوب الدخول المفضّل لدينا",
    ),
    LANDING_TITLE_LEAD(
        english = "Turn UAE market entry into a",
        simplifiedChinese = "让进入 UAE 市场成为一个",
        arabic = "حوّل دخول سوق الإمارات إلى",
    ),
    LANDING_TITLE_EMPHASIS(
        english = "clear, confident decision.",
        simplifiedChinese = "清晰而自信的决策。",
        arabic = "قرار واضح وواثق.",
    ),
    LANDING_LEAD(
        english = "Answer a few focused questions. We’ll assess market fit, identify a practical entry route and model the economics behind your next move.",
        simplifiedChinese = "回答几个重点问题。我们将评估市场契合度、确定切实可行的进入路径，并测算下一步行动背后的经济性。",
        arabic = "أجب عن بضعة أسئلة محددة. سنقيّم ملاءمة السوق، ونحدّد مسار دخول عملياً، ونبني نموذجاً للجدوى الاقتصادية لخطوتك التالية.",
    ),
    LANDING_START(
        english = "Start your assessment",
        simplifiedChinese = "开始评估",
        arabic = "ابدأ التقييم",
    ),
    LANDING_OUTPUTS_A11Y(
        english = "Analysis the client receives",
        simplifiedChinese = "客户将获得的分析",
        arabic = "التحليل الذي يتلقّاه العميل",
    ),
    LANDING_OUTPUTS_LABEL(
        english = "Assessment returns",
        simplifiedChinese = "评估结果",
        arabic = "نتائج التقييم",
    ),
    LANDING_OUTPUT_ELIGIBILITY(
        english = "Eligibility requirements",
        simplifiedChinese = "准入资格要求",
        arabic = "متطلبات الأهلية",
    ),
    LANDING_OUTPUT_LICENSING(
        english = "Recommended licensing route",
        simplifiedChinese = "建议的许可路径",
        arabic = "مسار الترخيص الموصى به",
    ),
    LANDING_OUTPUT_COSTS(
        english = "Setup and running costs",
        simplifiedChinese = "设立与运营成本",
        arabic = "تكاليف التأسيس والتشغيل",
    ),
    LANDING_OUTPUT_MARGIN(
        english = "Projected margin outlook",
        simplifiedChinese = "预计利润率前景",
        arabic = "توقعات هامش الربح",
    ),
    LANDING_OUTPUT_NEXT_STEPS(
        english = "Source-backed next steps",
        simplifiedChinese = "有来源依据的后续步骤",
        arabic = "خطوات تالية مدعومة بالمصادر",
    ),
    LANDING_EXPECTATIONS_A11Y(
        english = "What to expect from the assessment",
        simplifiedChinese = "本次评估将涵盖的内容",
        arabic = "ما الذي تتوقعه من التقييم",
    ),
    LANDING_EXPECTATION_FIT_TITLE(
        english = "Assess market fit",
        simplifiedChinese = "评估市场契合度",
        arabic = "قيّم ملاءمة السوق",
    ),
    LANDING_EXPECTATION_FIT_BODY(
        english = "Screen eligibility, sector conditions and demand signals for your business.",
        simplifiedChinese = "筛查企业的准入资格、行业条件和需求信号。",
        arabic = "افحص الأهلية وظروف القطاع ومؤشرات الطلب لنشاطك.",
    ),
    LANDING_EXPECTATION_ROUTE_TITLE(
        english = "Map your entry route",
        simplifiedChinese = "规划进入路径",
        arabic = "حدّد مسار دخولك",
    ),
    LANDING_EXPECTATION_ROUTE_BODY(
        english = "Clarify the licensing and operating path that best fits your plans.",
        simplifiedChinese = "明确最符合您计划的许可与运营路径。",
        arabic = "وضّح مسار الترخيص والتشغيل الأنسب لخططك.",
    ),
    LANDING_EXPECTATION_ECONOMICS_TITLE(
        english = "Model the economics",
        simplifiedChinese = "测算经济性",
        arabic = "حلّل الجدوى الاقتصادية",
    ),
    LANDING_EXPECTATION_ECONOMICS_BODY(
        english = "Compare setup costs, recurring costs and representative operating margins.",
        simplifiedChinese = "比较设立成本、经常性成本和代表性营业利润率。",
        arabic = "قارن تكاليف التأسيس والتكاليف المتكررة وهوامش التشغيل التمثيلية.",
    ),
    LANDING_EXPECTATION_BRIEF_TITLE(
        english = "Receive a decision brief",
        simplifiedChinese = "获取决策简报",
        arabic = "احصل على موجز للقرار",
    ),
    LANDING_EXPECTATION_BRIEF_BODY(
        english = "Review a clear recommendation backed by assumptions and linked sources.",
        simplifiedChinese = "查看由假设与关联来源支持的明确建议。",
        arabic = "راجع توصية واضحة مدعومة بالافتراضات والمصادر المرتبطة.",
    ),
    TRACK_TITLE(
        english = "What are you exploring?",
        simplifiedChinese = "您正在探索什么？",
        arabic = "ما الذي تستكشفه؟",
    ),
    TRACK_LEAD(
        english = "Choose the starting point that best reflects where your business is today. Both paths use the same market-entry engine; your selection simply changes how we prepare the company profile, product context and operating assumptions before evaluating eligibility, route options and economics.",
        simplifiedChinese = "请选择最符合企业当前阶段的起点。两条路径使用相同的市场进入分析引擎；您的选择只会改变我们在评估准入资格、路径选项和经济性之前，准备公司概况、产品背景与运营假设的方式。",
        arabic = "اختر نقطة البداية التي تعبّر بأفضل صورة عن وضع نشاطك اليوم. يستخدم المساران محرك دخول السوق نفسه؛ ولا يغيّر اختيارك سوى كيفية إعداد ملف الشركة وسياق المنتج وافتراضات التشغيل قبل تقييم الأهلية وخيارات المسار والجدوى الاقتصادية.",
    ),
    TRACK_COMPANY_TITLE(
        english = "Expand an existing company",
        simplifiedChinese = "拓展现有公司",
        arabic = "وسّع شركة قائمة",
    ),
    TRACK_COMPANY_BODY(
        english = "Use a curated company profile to assess a practical expansion scenario. We prefill the sector, product set and baseline operating assumptions so you can move quickly into route and economics analysis.",
        simplifiedChinese = "使用精选公司概况评估切实可行的扩张情景。我们会预先填入行业、产品组合和基准运营假设，以便您快速进入路径与经济性分析。",
        arabic = "استخدم ملف شركة مُعدّاً بعناية لتقييم سيناريو توسّع عملي. نملأ مسبقاً القطاع ومجموعة المنتجات وافتراضات التشغيل الأساسية كي تنتقل سريعاً إلى تحليل المسار والجدوى الاقتصادية.",
    ),
    TRACK_COMPANY_BENEFITS_A11Y(
        english = "Existing company route benefits",
        simplifiedChinese = "现有公司路径的优势",
        arabic = "مزايا مسار الشركة القائمة",
    ),
    TRACK_COMPANY_BENEFIT_OPERATING(
        english = "Designed for operating companies",
        simplifiedChinese = "专为已运营公司设计",
        arabic = "مصمم للشركات العاملة",
    ),
    TRACK_COMPANY_BENEFIT_CURATED(
        english = "Curated products and baseline assumptions",
        simplifiedChinese = "精选产品与基准假设",
        arabic = "منتجات مختارة وافتراضات أساسية",
    ),
    TRACK_COMPANY_BENEFIT_SPEED(
        english = "Faster path to market-route comparison",
        simplifiedChinese = "更快进入市场路径比较",
        arabic = "مسار أسرع لمقارنة طرق دخول السوق",
    ),
    TRACK_COMPANY_CONTINUE(
        english = "Continue with a company profile",
        simplifiedChinese = "使用公司概况继续",
        arabic = "تابع باستخدام ملف شركة",
    ),
    TRACK_STARTUP_TITLE(
        english = "Explore a startup concept",
        simplifiedChinese = "探索初创企业构想",
        arabic = "استكشف فكرة مشروع ناشئ",
    ),
    TRACK_STARTUP_BODY(
        english = "Start with a structured business archetype and test a new idea before committing resources. No business plan or free-form data entry is needed; choose the closest model and we’ll build a representative scenario.",
        simplifiedChinese = "从结构化业务原型开始，在投入资源前检验新构想。无需商业计划书或自由文本输入；选择最接近的模式，我们将构建一个代表性情景。",
        arabic = "ابدأ بنموذج أعمال منظّم واختبر فكرة جديدة قبل تخصيص الموارد. لا حاجة إلى خطة أعمال أو إدخال نص حر؛ اختر النموذج الأقرب وسنبني سيناريو تمثيلياً.",
    ),
    TRACK_STARTUP_BENEFITS_A11Y(
        english = "Startup concept route benefits",
        simplifiedChinese = "初创构想路径的优势",
        arabic = "مزايا مسار فكرة المشروع الناشئ",
    ),
    TRACK_STARTUP_BENEFIT_EARLY(
        english = "Designed for early-stage concepts",
        simplifiedChinese = "专为早期构想设计",
        arabic = "مصمم للأفكار في مراحلها المبكرة",
    ),
    TRACK_STARTUP_BENEFIT_GUIDED(
        english = "Guided archetypes with no blank-page setup",
        simplifiedChinese = "引导式业务原型，无需从空白开始",
        arabic = "نماذج إرشادية من دون إعداد من الصفر",
    ),
    TRACK_STARTUP_BENEFIT_FEASIBILITY(
        english = "Test feasibility before committing resources",
        simplifiedChinese = "投入资源前先检验可行性",
        arabic = "اختبر الجدوى قبل تخصيص الموارد",
    ),
    TRACK_STARTUP_CONTINUE(
        english = "Continue with a startup scenario",
        simplifiedChinese = "使用初创企业情景继续",
        arabic = "تابع باستخدام سيناريو مشروع ناشئ",
    ),
    COMPANY_TITLE(
        english = "Choose a company to assess.",
        simplifiedChinese = "选择一家要评估的公司。",
        arabic = "اختر شركة لتقييمها.",
    ),
    COMPANY_LEAD(
        english = "Choose a company card to open its representative products and applicable UAE entry routes. Each profile shows how sector, product and operating-model differences shape the market-entry decision.",
        simplifiedChinese = "选择公司卡片，查看其代表性产品及适用的 UAE 市场进入路径。每份概况都会展示行业、产品和运营模式的差异如何影响市场进入决策。",
        arabic = "اختر بطاقة شركة لعرض منتجاتها التمثيلية ومسارات دخول سوق الإمارات المنطبقة عليها. يوضّح كل ملف كيف تؤثر اختلافات القطاع والمنتج ونموذج التشغيل في قرار دخول السوق.",
    ),
    COMPANY_PROFILES_A11Y(
        english = "Company profiles",
        simplifiedChinese = "公司概况",
        arabic = "ملفات الشركات",
    ),
    COMPANY_CAVA(
        english = "CAVA",
        simplifiedChinese = "CAVA",
        arabic = "CAVA",
    ),
    COMPANY_CAVA_DESCRIPTION(
        english = "Mediterranean fast-casual restaurant brand serving customizable bowls and pitas, with dips, spreads and dressings also sold through grocery.",
        simplifiedChinese = "地中海风味快休闲餐饮品牌，提供可定制的碗餐和皮塔饼，并通过杂货零售渠道销售蘸酱、抹酱和沙拉酱。",
        arabic = "علامة مطاعم متوسطية سريعة غير رسمية تقدّم أطباقاً وخبز بيتا قابلة للتخصيص، كما تبيع التغميسات والمنتجات القابلة للدهن والصلصات عبر متاجر البقالة.",
    ),
    COMPANY_CAVA_LOCATION(
        english = "Washington, D.C. headquarters · Restaurant locations across the United States",
        simplifiedChinese = "总部位于美国华盛顿特区 · 餐厅遍布美国各地",
        arabic = "المقر الرئيسي في واشنطن العاصمة · مطاعم في أنحاء الولايات المتحدة",
    ),
    COMPANY_CAVA_TAG_RESTAURANTS(
        english = "Restaurants",
        simplifiedChinese = "餐厅",
        arabic = "مطاعم",
    ),
    COMPANY_CAVA_TAG_FOOD(
        english = "Consumer food",
        simplifiedChinese = "消费食品",
        arabic = "أغذية استهلاكية",
    ),
    COMPANY_WARBY_PARKER(
        english = "Warby Parker",
        simplifiedChinese = "Warby Parker",
        arabic = "Warby Parker",
    ),
    COMPANY_WARBY_PARKER_DESCRIPTION(
        english = "Omnichannel eyewear and vision-care company offering prescription glasses, sunglasses, contact lenses, eye exams and digital vision tools.",
        simplifiedChinese = "全渠道眼镜与视力护理公司，提供处方眼镜、太阳镜、隐形眼镜、视力检查和数字化视力工具。",
        arabic = "شركة متعددة القنوات للنظارات ورعاية البصر، تقدّم نظارات طبية وشمسية وعدسات لاصقة وفحوصات للعين وأدوات رقمية للرؤية.",
    ),
    COMPANY_WARBY_PARKER_LOCATION(
        english = "New York City headquarters · Retail stores across the United States and Canada",
        simplifiedChinese = "总部位于纽约市 · 零售门店遍布美国和加拿大",
        arabic = "المقر الرئيسي في مدينة نيويورك · متاجر تجزئة في أنحاء الولايات المتحدة وكندا",
    ),
    COMPANY_WARBY_PARKER_TAG_EYEWEAR(
        english = "Eyewear",
        simplifiedChinese = "眼镜",
        arabic = "نظارات",
    ),
    COMPANY_WARBY_PARKER_TAG_RETAIL(
        english = "Omnichannel retail",
        simplifiedChinese = "全渠道零售",
        arabic = "تجزئة متعددة القنوات",
    ),
    COMPANY_FRESHPET(
        english = "Freshpet",
        simplifiedChinese = "Freshpet",
        arabic = "Freshpet",
    ),
    COMPANY_FRESHPET_DESCRIPTION(
        english = "Manufacturer, marketer and distributor of fresh dog food, cat food and dog treats sold through retail and distributor channels.",
        simplifiedChinese = "新鲜犬粮、猫粮和犬类零食的制造、营销和分销商，产品通过零售和经销渠道销售。",
        arabic = "مصنّع ومسوق وموزّع لأغذية طازجة للكلاب والقطط ومكافآت للكلاب تُباع عبر قنوات التجزئة والتوزيع.",
    ),
    COMPANY_FRESHPET_LOCATION(
        english = "Bedminster, New Jersey headquarters · Kitchens in Bethlehem, Pennsylvania and Ennis, Texas",
        simplifiedChinese = "总部位于新泽西州贝德明斯特 · 厨房设于宾夕法尼亚州伯利恒和得克萨斯州恩尼斯",
        arabic = "المقر الرئيسي في بيدمينستر، نيوجيرسي · مطابخ في بيت لحم، بنسلفانيا، وإينيس، تكساس",
    ),
    COMPANY_FRESHPET_TAG_PET_FOOD(
        english = "Pet food",
        simplifiedChinese = "宠物食品",
        arabic = "أغذية الحيوانات الأليفة",
    ),
    COMPANY_FRESHPET_TAG_COLD_CHAIN(
        english = "Cold chain",
        simplifiedChinese = "冷链",
        arabic = "سلسلة تبريد",
    ),
    COMPANY_TOAST(
        english = "Toast",
        simplifiedChinese = "Toast",
        arabic = "Toast",
    ),
    COMPANY_TOAST_DESCRIPTION(
        english = "Cloud-based, all-in-one technology platform for restaurants and food-and-beverage retailers, combining SaaS, integrated payments, financial technology and purpose-built hardware.",
        simplifiedChinese = "面向餐厅及食品饮料零售商的一体化云技术平台，结合 SaaS、集成支付、金融科技和专用硬件。",
        arabic = "منصة تقنية سحابية متكاملة للمطاعم وتجار الأغذية والمشروبات، تجمع بين البرمجيات كخدمة والمدفوعات المتكاملة والتقنية المالية والأجهزة المخصصة.",
    ),
    COMPANY_TOAST_LOCATION(
        english = "Boston headquarters · Offices across North America, Europe and Asia",
        simplifiedChinese = "总部位于波士顿 · 办事处遍布北美、欧洲和亚洲",
        arabic = "المقر الرئيسي في بوسطن · مكاتب في أميركا الشمالية وأوروبا وآسيا",
    ),
    COMPANY_TOAST_TAG_TECH(
        english = "Restaurant tech",
        simplifiedChinese = "餐饮科技",
        arabic = "تقنيات المطاعم",
    ),
    COMPANY_TOAST_TAG_HARDWARE(
        english = "Software + hardware",
        simplifiedChinese = "软件 + 硬件",
        arabic = "برمجيات + أجهزة",
    ),
    PROFILE_TITLE(
        english = "Shape your business profile.",
        simplifiedChinese = "构建您的业务概况。",
        arabic = "شكّل ملف نشاطك.",
    ),
    PROFILE_LEAD(
        english = "Choose the closest match for the venture you plan to launch or grow in the UAE.",
        simplifiedChinese = "请选择与您计划在 UAE 启动或发展的企业最接近的选项。",
        arabic = "اختر الوصف الأقرب للمشروع الذي تخطط لإطلاقه أو تنميته في الإمارات.",
    ),
    STARTUP_PROFILE_NAME_TEMPLATE(
        english = "{subindustry} venture",
        simplifiedChinese = "{subindustry} 项目",
        arabic = "مشروع {subindustry}",
    ),
    STARTUP_PRODUCT_NAME_TEMPLATE(
        english = "{subindustry} · {productType}",
        simplifiedChinese = "{subindustry} · {productType}",
        arabic = "{subindustry} · {productType}",
    ),
    PROFILE_META(
        english = "Five structured inputs · no free text",
        simplifiedChinese = "五项结构化输入 · 无需自由文本",
        arabic = "خمسة مدخلات منظّمة · من دون نص حر",
    ),
    ALL_FIELDS_REQUIRED(
        english = "All fields required",
        simplifiedChinese = "所有字段均为必填项",
        arabic = "جميع الحقول مطلوبة",
    ),
    FIELD_INDUSTRY(
        english = "Industry",
        simplifiedChinese = "行业",
        arabic = "القطاع",
    ),
    FIELD_INDUSTRY_HINT(
        english = "Which sector best describes the business?",
        simplifiedChinese = "哪个行业最符合该业务？",
        arabic = "أي قطاع يصف النشاط على أفضل وجه؟",
    ),
    FIELD_INDUSTRY_PLACEHOLDER(
        english = "Select an industry…",
        simplifiedChinese = "选择行业…",
        arabic = "اختر قطاعاً…",
    ),
    INDUSTRY_TECHNOLOGY(
        english = "Technology",
        simplifiedChinese = "科技",
        arabic = "التقنية",
    ),
    INDUSTRY_FOOD_BEVERAGE(
        english = "Food & Beverage",
        simplifiedChinese = "食品与饮料",
        arabic = "الأغذية والمشروبات",
    ),
    INDUSTRY_CONSUMER_GOODS(
        english = "Consumer Goods",
        simplifiedChinese = "消费品",
        arabic = "السلع الاستهلاكية",
    ),
    INDUSTRY_HEALTHCARE_WELLNESS(
        english = "Healthcare & Wellness",
        simplifiedChinese = "医疗健康",
        arabic = "الرعاية الصحية والعافية",
    ),
    INDUSTRY_PROFESSIONAL_SERVICES(
        english = "Professional Services",
        simplifiedChinese = "专业服务",
        arabic = "الخدمات المهنية",
    ),
    INDUSTRY_INDUSTRIAL_MANUFACTURING(
        english = "Industrial & Manufacturing",
        simplifiedChinese = "工业与制造业",
        arabic = "الصناعة والتصنيع",
    ),
    INDUSTRY_RETAIL_ECOMMERCE(
        english = "Retail & E-commerce",
        simplifiedChinese = "零售与电子商务",
        arabic = "التجزئة والتجارة الإلكترونية",
    ),
    FIELD_SUBINDUSTRY(
        english = "Sub-industry",
        simplifiedChinese = "细分行业",
        arabic = "القطاع الفرعي",
    ),
    FIELD_SUBINDUSTRY_HINT(
        english = "Used to match activities, approvals and market signals.",
        simplifiedChinese = "用于匹配经营活动、审批要求和市场信号。",
        arabic = "يُستخدم لمطابقة الأنشطة والموافقات ومؤشرات السوق.",
    ),
    FIELD_SUBINDUSTRY_FIRST_PLACEHOLDER(
        english = "Select an industry first…",
        simplifiedChinese = "请先选择行业…",
        arabic = "اختر القطاع أولاً…",
    ),
    FIELD_SUBINDUSTRY_PLACEHOLDER(
        english = "Select a sub-industry…",
        simplifiedChinese = "选择细分行业…",
        arabic = "اختر قطاعاً فرعياً…",
    ),
    SUBINDUSTRY_B2B_SAAS(
        english = "B2B SaaS",
        simplifiedChinese = "B2B SaaS",
        arabic = "برمجيات كخدمة للشركات (B2B SaaS)",
    ),
    SUBINDUSTRY_AI_DATA(
        english = "AI & data tools",
        simplifiedChinese = "AI 与数据工具",
        arabic = "أدوات الذكاء الاصطناعي والبيانات",
    ),
    SUBINDUSTRY_FINTECH_PAYMENTS(
        english = "Fintech & payments",
        simplifiedChinese = "金融科技与支付",
        arabic = "التقنية المالية والمدفوعات",
    ),
    SUBINDUSTRY_MARKETPLACES_PLATFORMS(
        english = "Marketplaces & platforms",
        simplifiedChinese = "交易市场与平台",
        arabic = "الأسواق الإلكترونية والمنصات",
    ),
    SUBINDUSTRY_RESTAURANT_CAFE(
        english = "Restaurant or café",
        simplifiedChinese = "餐厅或咖啡馆",
        arabic = "مطعم أو مقهى",
    ),
    SUBINDUSTRY_PACKAGED_FOOD(
        english = "Packaged food",
        simplifiedChinese = "包装食品",
        arabic = "أغذية معبأة",
    ),
    SUBINDUSTRY_BEVERAGES(
        english = "Beverages",
        simplifiedChinese = "饮料",
        arabic = "مشروبات",
    ),
    SUBINDUSTRY_CATERING_CLOUD_KITCHEN(
        english = "Catering or cloud kitchen",
        simplifiedChinese = "餐饮服务或云厨房",
        arabic = "تموين أو مطبخ سحابي",
    ),
    SUBINDUSTRY_BEAUTY_PERSONAL_CARE(
        english = "Beauty & personal care",
        simplifiedChinese = "美容与个人护理",
        arabic = "الجمال والعناية الشخصية",
    ),
    SUBINDUSTRY_APPAREL_ACCESSORIES(
        english = "Apparel & accessories",
        simplifiedChinese = "服装与配饰",
        arabic = "الملابس والإكسسوارات",
    ),
    SUBINDUSTRY_HOME_LIFESTYLE(
        english = "Home & lifestyle",
        simplifiedChinese = "家居与生活方式",
        arabic = "المنزل ونمط الحياة",
    ),
    SUBINDUSTRY_PET_PRODUCTS(
        english = "Pet products",
        simplifiedChinese = "宠物用品",
        arabic = "منتجات الحيوانات الأليفة",
    ),
    SUBINDUSTRY_DIGITAL_HEALTH(
        english = "Digital health",
        simplifiedChinese = "数字医疗",
        arabic = "الصحة الرقمية",
    ),
    SUBINDUSTRY_MEDICAL_DEVICES(
        english = "Medical devices",
        simplifiedChinese = "医疗器械",
        arabic = "الأجهزة الطبية",
    ),
    SUBINDUSTRY_FITNESS_WELLNESS(
        english = "Fitness & wellness",
        simplifiedChinese = "健身与健康",
        arabic = "اللياقة والعافية",
    ),
    SUBINDUSTRY_OPTICAL_VISION(
        english = "Optical & vision care",
        simplifiedChinese = "眼镜与视力护理",
        arabic = "البصريات ورعاية البصر",
    ),
    SUBINDUSTRY_CONSULTING(
        english = "Consulting",
        simplifiedChinese = "咨询",
        arabic = "الاستشارات",
    ),
    SUBINDUSTRY_MARKETING_CREATIVE(
        english = "Marketing & creative",
        simplifiedChinese = "市场营销与创意",
        arabic = "التسويق والإبداع",
    ),
    SUBINDUSTRY_EDUCATION_TRAINING(
        english = "Education & training",
        simplifiedChinese = "教育与培训",
        arabic = "التعليم والتدريب",
    ),
    SUBINDUSTRY_RECRUITMENT_HR(
        english = "Recruitment & HR",
        simplifiedChinese = "招聘与人力资源",
        arabic = "التوظيف والموارد البشرية",
    ),
    SUBINDUSTRY_LIGHT_MANUFACTURING(
        english = "Light manufacturing",
        simplifiedChinese = "轻型制造",
        arabic = "التصنيع الخفيف",
    ),
    SUBINDUSTRY_BUILDING_TECHNOLOGY(
        english = "Building technology",
        simplifiedChinese = "建筑科技",
        arabic = "تقنيات البناء",
    ),
    SUBINDUSTRY_MOBILITY_COMPONENTS(
        english = "Mobility components",
        simplifiedChinese = "出行设备零部件",
        arabic = "مكوّنات التنقل",
    ),
    SUBINDUSTRY_INDUSTRIAL_EQUIPMENT(
        english = "Industrial equipment",
        simplifiedChinese = "工业设备",
        arabic = "المعدات الصناعية",
    ),
    SUBINDUSTRY_DIRECT_TO_CONSUMER(
        english = "Direct-to-consumer brand",
        simplifiedChinese = "直面消费者品牌",
        arabic = "علامة تجارية مباشرة للمستهلك",
    ),
    SUBINDUSTRY_SPECIALTY_RETAIL(
        english = "Specialty retail",
        simplifiedChinese = "专业零售",
        arabic = "تجزئة متخصصة",
    ),
    SUBINDUSTRY_OMNICHANNEL_RETAIL(
        english = "Omnichannel retail",
        simplifiedChinese = "全渠道零售",
        arabic = "تجزئة متعددة القنوات",
    ),
    SUBINDUSTRY_WHOLESALE_DISTRIBUTION(
        english = "Wholesale & distribution",
        simplifiedChinese = "批发与分销",
        arabic = "الجملة والتوزيع",
    ),
    FIELD_PRODUCT_TYPE(
        english = "Product type",
        simplifiedChinese = "产品类型",
        arabic = "نوع المنتج",
    ),
    FIELD_PRODUCT_TYPE_HINT(
        english = "What will customers buy?",
        simplifiedChinese = "客户将购买什么？",
        arabic = "ماذا سيشتري العملاء؟",
    ),
    FIELD_PRODUCT_TYPE_PLACEHOLDER(
        english = "Select a product type…",
        simplifiedChinese = "选择产品类型…",
        arabic = "اختر نوع المنتج…",
    ),
    PRODUCT_TYPE_SOFTWARE(
        english = "Software / digital platform",
        simplifiedChinese = "软件 / 数字平台",
        arabic = "برمجيات / منصة رقمية",
    ),
    PRODUCT_TYPE_SERVICE(
        english = "Professional / managed service",
        simplifiedChinese = "专业 / 托管服务",
        arabic = "خدمة مهنية / مُدارة",
    ),
    PRODUCT_TYPE_PHYSICAL(
        english = "Physical consumer product",
        simplifiedChinese = "实体消费品",
        arabic = "منتج استهلاكي مادي",
    ),
    PRODUCT_TYPE_FOOD(
        english = "Food / beverage product",
        simplifiedChinese = "食品 / 饮料产品",
        arabic = "منتج غذائي / مشروب",
    ),
    PRODUCT_TYPE_HARDWARE(
        english = "Hardware / connected device",
        simplifiedChinese = "硬件 / 联网设备",
        arabic = "جهاز / جهاز متصل",
    ),
    PRODUCT_TYPE_LOCAL_SERVICE(
        english = "Local venue / in-person service",
        simplifiedChinese = "本地场所 / 线下服务",
        arabic = "موقع محلي / خدمة حضورية",
    ),
    PRODUCT_TYPE_MARKETPLACE(
        english = "Marketplace / transaction platform",
        simplifiedChinese = "交易市场 / 交易平台",
        arabic = "سوق إلكتروني / منصة معاملات",
    ),
    FIELD_CUSTOMER(
        english = "Primary customer",
        simplifiedChinese = "主要客户",
        arabic = "العميل الأساسي",
    ),
    FIELD_CUSTOMER_HINT(
        english = "Who is the primary buyer?",
        simplifiedChinese = "主要买方是谁？",
        arabic = "من هو المشتري الأساسي؟",
    ),
    FIELD_CUSTOMER_PLACEHOLDER(
        english = "Select a customer type…",
        simplifiedChinese = "选择客户类型…",
        arabic = "اختر نوع العميل…",
    ),
    CUSTOMER_B2B(
        english = "Businesses (B2B)",
        simplifiedChinese = "企业（B2B）",
        arabic = "الشركات (B2B)",
    ),
    CUSTOMER_B2C(
        english = "Consumers (B2C)",
        simplifiedChinese = "消费者（B2C）",
        arabic = "المستهلكون (B2C)",
    ),
    CUSTOMER_B2G(
        english = "Government / public sector (B2G)",
        simplifiedChinese = "政府 / 公共部门（B2G）",
        arabic = "الحكومة / القطاع العام (B2G)",
    ),
    CUSTOMER_HOSPITALITY(
        english = "Hospitality operators",
        simplifiedChinese = "酒店与餐饮运营商",
        arabic = "مشغّلو الضيافة",
    ),
    CUSTOMER_CHANNEL(
        english = "Retailers / distributors",
        simplifiedChinese = "零售商 / 分销商",
        arabic = "تجّار التجزئة / الموزّعون",
    ),
    CUSTOMER_MIXED(
        english = "Mixed business and consumer",
        simplifiedChinese = "企业与消费者混合客户",
        arabic = "مزيج من الشركات والمستهلكين",
    ),
    FIELD_VALIDATION(
        english = "UAE customer validation",
        simplifiedChinese = "UAE 客户验证",
        arabic = "التحقق من طلب عملاء الإمارات",
    ),
    FIELD_VALIDATION_HINT(
        english = "What is your strongest UAE-specific evidence?",
        simplifiedChinese = "您最有力的 UAE 市场专项证据是什么？",
        arabic = "ما أقوى دليل لديك خاص بسوق الإمارات؟",
    ),
    FIELD_VALIDATION_PLACEHOLDER(
        english = "Select a validation stage…",
        simplifiedChinese = "选择验证阶段…",
        arabic = "اختر مرحلة التحقق…",
    ),
    VALIDATION_NONE(
        english = "No UAE validation yet",
        simplifiedChinese = "尚无 UAE 市场验证",
        arabic = "لا يوجد تحقق في الإمارات حتى الآن",
    ),
    VALIDATION_RESEARCH(
        english = "UAE market research or inbound interest",
        simplifiedChinese = "UAE 市场调研或主动咨询意向",
        arabic = "بحث لسوق الإمارات أو اهتمام وارد",
    ),
    VALIDATION_INTERVIEWS(
        english = "UAE customer interviews completed",
        simplifiedChinese = "已完成 UAE 客户访谈",
        arabic = "اكتملت مقابلات عملاء في الإمارات",
    ),
    VALIDATION_PILOT(
        english = "Signed LOI, pilot or distributor interest",
        simplifiedChinese = "已签署意向书（LOI）、开展试点或获得分销商意向",
        arabic = "خطاب نوايا موقّع أو تجربة أولية أو اهتمام موزّع",
    ),
    VALIDATION_PAYING(
        english = "Paying UAE customers or repeat sales",
        simplifiedChinese = "已有付费 UAE 客户或复购销售",
        arabic = "عملاء يدفعون في الإمارات أو مبيعات متكررة",
    ),
    PROFILE_COMPLETE(
        english = "Profile complete · ready to continue",
        simplifiedChinese = "概况已完成 · 可以继续",
        arabic = "اكتمل الملف · جاهز للمتابعة",
    ),
    PROFILE_CONTINUE(
        english = "Continue to planning ranges",
        simplifiedChinese = "继续设置规划区间",
        arabic = "تابع إلى نطاقات التخطيط",
    ),
    PROFILE_PROGRESS_TEMPLATE(
        english = "{answered} of 5 answered · complete all fields",
        simplifiedChinese = "已回答 {answered}/5 · 请完成所有字段",
        arabic = "تمت الإجابة عن {answered} من 5 · أكمل جميع الحقول",
    ),
    PRODUCT_ROUTE_TITLE_TEMPLATE(
        english = "Explore {company} products and entry routes.",
        simplifiedChinese = "探索 {company} 的产品与进入路径。",
        arabic = "استكشف منتجات {company} ومسارات الدخول.",
    ),
    PRODUCT_ROUTE_LEAD(
        english = "Compare the representative product offers below and select the UAE route you want to model. Your selection will carry into the Dubai and Abu Dhabi comparison.",
        simplifiedChinese = "比较下方的代表性产品方案，并选择要建模的 UAE 市场进入路径。您的选择将用于 Dubai 与 Abu Dhabi 的比较。",
        arabic = "قارن عروض المنتجات التمثيلية أدناه واختر مسار الإمارات الذي تريد نمذجته. سينتقل اختيارك إلى المقارنة بين دبي وأبوظبي.",
    ),
    PRODUCTS_FROM(
        english = "Products from",
        simplifiedChinese = "产品来自",
        arabic = "منتجات من",
    ),
    ENTRY_ROUTE_FOR_PRODUCT(
        english = "Entry route for the selected product",
        simplifiedChinese = "所选产品的进入路径",
        arabic = "مسار الدخول للمنتج المحدد",
    ),
    ROUTE_REMOTE_TITLE(
        english = "Remote / digital sales",
        simplifiedChinese = "远程 / 数字销售",
        arabic = "مبيعات عن بُعد / رقمية",
    ),
    ROUTE_REMOTE_BODY(
        english = "Serve UAE customers digitally without importing physical goods or establishing a local operating footprint.",
        simplifiedChinese = "通过数字方式服务 UAE 客户，无需进口实体商品或建立本地运营实体。",
        arabic = "اخدم عملاء الإمارات رقمياً من دون استيراد سلع مادية أو إنشاء حضور تشغيلي محلي.",
    ),
    ROUTE_IMPORT_TITLE(
        english = "Import, distribute & sell",
        simplifiedChinese = "进口、分销与销售",
        arabic = "استيراد وتوزيع وبيع",
    ),
    ROUTE_IMPORT_BODY(
        english = "Bring finished physical products into the UAE through an approved importer, distributor and sales channel.",
        simplifiedChinese = "通过获批的进口商、分销商和销售渠道，将实体成品引入 UAE。",
        arabic = "أدخل المنتجات المادية النهائية إلى الإمارات عبر مستورد وموزّع وقناة بيع معتمدين.",
    ),
    ROUTE_LOCAL_TITLE(
        english = "Local operations or production + sales",
        simplifiedChinese = "本地运营或生产 + 销售",
        arabic = "عمليات أو إنتاج محلي + مبيعات",
    ),
    ROUTE_LOCAL_BODY(
        english = "Operate stores, services, assembly or production in the UAE through an appropriate local structure.",
        simplifiedChinese = "通过适当的本地架构，在 UAE 运营门店、服务、组装或生产业务。",
        arabic = "شغّل متاجر أو خدمات أو أعمال تجميع أو إنتاج في الإمارات من خلال هيكل محلي مناسب.",
    ),
    ROUTE_CHOOSE_NOTE(
        english = "Choose the entry route you want to compare across Dubai and Abu Dhabi.",
        simplifiedChinese = "选择您希望在 Dubai 与 Abu Dhabi 之间比较的进入路径。",
        arabic = "اختر مسار الدخول الذي تريد مقارنته بين دبي وأبوظبي.",
    ),
    RUN_CITY_ANALYSIS(
        english = "Run city analysis",
        simplifiedChinese = "运行城市分析",
        arabic = "شغّل تحليل المدينتين",
    ),
    PRODUCT_CAVA_BUILD_YOUR_OWN(
        english = "Build-your-own bowls & pitas",
        simplifiedChinese = "自选搭配碗餐与皮塔饼",
        arabic = "أطباق وخبز بيتا حسب الطلب",
    ),
    PRODUCT_CAVA_BUILD_YOUR_OWN_DESCRIPTION(
        english = "Customizable restaurant offer assembled to order; requires local kitchens, food-service licensing, trained staff and local sales.",
        simplifiedChinese = "按订单现场搭配的可定制餐饮方案；需要本地厨房、餐饮服务许可、受训员工和本地销售。",
        arabic = "عرض مطعم قابل للتخصيص يُحضّر حسب الطلب؛ ويتطلب مطابخ محلية وترخيص خدمات غذائية وموظفين مدرّبين ومبيعات محلية.",
    ),
    PRODUCT_CAVA_CHEF_CURATED(
        english = "Chef-curated bowls & pitas",
        simplifiedChinese = "主厨精选碗餐与皮塔饼",
        arabic = "أطباق وخبز بيتا من اختيار الطاهي",
    ),
    PRODUCT_CAVA_CHEF_CURATED_DESCRIPTION(
        english = "Chef-designed menu combinations prepared and sold through a locally operated restaurant network.",
        simplifiedChinese = "由主厨设计的菜单组合，通过本地运营的餐厅网络制作与销售。",
        arabic = "تشكيلات قوائم صممها الطاهي، تُحضّر وتباع عبر شبكة مطاعم تُشغّل محلياً.",
    ),
    PRODUCT_CAVA_CATERING(
        english = "Catering / Group Bowl Bar",
        simplifiedChinese = "餐饮服务 / 团体自选碗餐吧",
        arabic = "تموين / بار أطباق للمجموعات",
    ),
    PRODUCT_CAVA_CATERING_DESCRIPTION(
        english = "Group ordering and catering service fulfilled from local kitchens for offices, events and larger gatherings.",
        simplifiedChinese = "由本地厨房为办公室、活动和大型聚会履约的团体订餐与餐饮服务。",
        arabic = "خدمة طلبات جماعية وتموين تُنفّذ من مطابخ محلية للمكاتب والفعاليات والتجمعات الكبيرة.",
    ),
    PRODUCT_WARBY_PRESCRIPTION(
        english = "Prescription eyeglasses",
        simplifiedChinese = "处方眼镜",
        arabic = "نظارات طبية",
    ),
    PRODUCT_WARBY_PRESCRIPTION_DESCRIPTION(
        english = "Corrective eyewear requiring prescription validation, product compliance, optical dispensing and potentially licensed local eye-care support.",
        simplifiedChinese = "需要验证处方、满足产品合规要求、进行专业配镜，并可能需要持牌本地眼科护理支持的矫正眼镜。",
        arabic = "نظارات تصحيحية تتطلب التحقق من الوصفة وامتثال المنتج وصرفاً بصرياً، وربما دعماً محلياً مرخّصاً لرعاية العيون.",
    ),
    PRODUCT_WARBY_SUNGLASSES(
        english = "Non-prescription sunglasses",
        simplifiedChinese = "非处方太阳镜",
        arabic = "نظارات شمسية بلا وصفة",
    ),
    PRODUCT_WARBY_SUNGLASSES_DESCRIPTION(
        english = "Consumer eyewear suited to e-commerce, wholesale or branded retail with standard product-compliance checks.",
        simplifiedChinese = "适用于电子商务、批发或品牌零售，并需进行标准产品合规检查的消费类眼镜。",
        arabic = "نظارات استهلاكية ملائمة للتجارة الإلكترونية أو الجملة أو التجزئة ذات العلامة التجارية، مع فحوصات امتثال قياسية للمنتج.",
    ),
    PRODUCT_WARBY_CONTACTS(
        english = "Contact lenses",
        simplifiedChinese = "隐形眼镜",
        arabic = "عدسات لاصقة",
    ),
    PRODUCT_WARBY_CONTACTS_DESCRIPTION(
        english = "Regulated vision-care products requiring approvals, prescription handling and controlled retail or clinical channels.",
        simplifiedChinese = "受监管的视力护理产品，需要审批、处方管理以及受控零售或临床渠道。",
        arabic = "منتجات رعاية بصرية منظّمة تتطلب موافقات ومعالجة للوصفات وقنوات تجزئة أو قنوات سريرية خاضعة للرقابة.",
    ),
    PRODUCT_FRESHPET_ROLLS(
        english = "Refrigerated dog-food rolls",
        simplifiedChinese = "冷藏犬粮卷",
        arabic = "لفائف طعام كلاب مبردة",
    ),
    PRODUCT_FRESHPET_ROLLS_DESCRIPTION(
        english = "Fresh pet-food rolls requiring an uninterrupted cold chain, shelf-life controls and compliant product registration.",
        simplifiedChinese = "需要不间断冷链、保质期控制和合规产品注册的新鲜宠物食品卷。",
        arabic = "لفائف طعام طازج للحيوانات الأليفة تتطلب سلسلة تبريد متواصلة وضوابط لمدة الصلاحية وتسجيل منتج ممتثل.",
    ),
    PRODUCT_FRESHPET_BAGGED_MEALS(
        english = "Refrigerated bagged dog meals",
        simplifiedChinese = "冷藏袋装犬粮",
        arabic = "وجبات كلاب مبردة معبأة في أكياس",
    ),
    PRODUCT_FRESHPET_BAGGED_MEALS_DESCRIPTION(
        english = "Ready-to-serve fresh dog meals dependent on temperature-controlled shipping, storage and refrigerated retail display.",
        simplifiedChinese = "即食新鲜犬粮，依赖温控运输、储存和冷藏零售陈列。",
        arabic = "وجبات كلاب طازجة جاهزة للتقديم تعتمد على شحن وتخزين مضبوطَي الحرارة وعرض مبرد في متاجر التجزئة.",
    ),
    PRODUCT_FRESHPET_CAT_FOOD(
        english = "Refrigerated cat food",
        simplifiedChinese = "冷藏猫粮",
        arabic = "طعام قطط مبرد",
    ),
    PRODUCT_FRESHPET_CAT_FOOD_DESCRIPTION(
        english = "Fresh cat food with cold-chain, shelf-life and animal-feed compliance requirements throughout distribution.",
        simplifiedChinese = "在整个分销过程中须满足冷链、保质期和动物饲料合规要求的新鲜猫粮。",
        arabic = "طعام قطط طازج يخضع لمتطلبات سلسلة التبريد ومدة الصلاحية وامتثال أعلاف الحيوانات على امتداد التوزيع.",
    ),
    PRODUCT_TOAST_POS(
        english = "Restaurant POS software",
        simplifiedChinese = "餐厅 POS 软件",
        arabic = "برنامج نقاط بيع للمطاعم",
    ),
    PRODUCT_TOAST_POS_DESCRIPTION(
        english = "Cloud-based point-of-sale, payments and restaurant-management software delivered digitally to operators.",
        simplifiedChinese = "通过数字方式向运营商提供的云端销售点、支付和餐厅管理软件。",
        arabic = "برنامج سحابي لنقاط البيع والمدفوعات وإدارة المطاعم يُقدّم رقمياً للمشغّلين.",
    ),
    PRODUCT_TOAST_GO(
        english = "Toast Go 3 handheld + platform",
        simplifiedChinese = "Toast Go 3 手持设备 + 平台",
        arabic = "جهاز Toast Go 3 المحمول + المنصة",
    ),
    PRODUCT_TOAST_GO_DESCRIPTION(
        english = "Handheld restaurant ordering and payment device bundled with the Toast platform and payment services.",
        simplifiedChinese = "与 Toast 平台和支付服务捆绑的餐厅手持点餐与支付设备。",
        arabic = "جهاز محمول لطلبات المطاعم ومدفوعاتها يأتي مع منصة Toast وخدمات الدفع.",
    ),
    PRODUCT_TOAST_STOREFRONT(
        english = "Online Ordering & Digital Storefront",
        simplifiedChinese = "在线点餐与数字店面",
        arabic = "الطلب عبر الإنترنت والواجهة الرقمية",
    ),
    PRODUCT_TOAST_STOREFRONT_DESCRIPTION(
        english = "Digital ordering and branded storefront tools that connect restaurants directly with guests.",
        simplifiedChinese = "让餐厅可直接触达顾客的数字点餐与品牌店面工具。",
        arabic = "أدوات طلب رقمية وواجهات تحمل العلامة التجارية تربط المطاعم بالضيوف مباشرة.",
    ),
    SCALE_TITLE(
        english = "Set the scale of your first year.",
        simplifiedChinese = "设定第一年的业务规模。",
        arabic = "حدّد نطاق عامك الأول.",
    ),
    SCALE_LEAD(
        english = "Use planning ranges rather than exact forecasts. These answers shape the cost, team and operating assumptions used in your Dubai and Abu Dhabi comparison.",
        simplifiedChinese = "请使用规划区间而非精确预测。这些回答将影响 Dubai 与 Abu Dhabi 比较中使用的成本、团队和运营假设。",
        arabic = "استخدم نطاقات تخطيط بدلاً من توقعات دقيقة. تحدّد هذه الإجابات افتراضات التكلفة والفريق والتشغيل المستخدمة في المقارنة بين دبي وأبوظبي.",
    ),
    SCALE_PLAN_A11Y(
        english = "Operating plan assumptions from your business profile",
        simplifiedChinese = "根据您的业务概况生成的运营计划假设",
        arabic = "افتراضات خطة التشغيل المستمدة من ملف نشاطك",
    ),
    SCALE_OPERATING_PLAN(
        english = "Operating plan",
        simplifiedChinese = "运营计划",
        arabic = "خطة التشغيل",
    ),
    SCALE_FLEXIBLE_SETUP(
        english = "Flexible operating setup",
        simplifiedChinese = "灵活的运营配置",
        arabic = "إعداد تشغيلي مرن",
    ),
    SCALE_INPUTS_FROM_OFFER(
        english = "Inputs based on your offer",
        simplifiedChinese = "根据您的方案确定投入要素",
        arabic = "مدخلات مبنية على عرضك",
    ),
    SCALE_CUSTOMER_DATA_REVIEW(
        english = "Customer data review",
        simplifiedChinese = "客户数据审查",
        arabic = "مراجعة بيانات العملاء",
    ),
    SCALE_EVIDENCE_REVIEW(
        english = "Evidence review required",
        simplifiedChinese = "需要审查证据",
        arabic = "يلزم مراجعة الأدلة",
    ),
    SCALE_META(
        english = "Four planning ranges · no free text",
        simplifiedChinese = "四项规划区间 · 无需自由文本",
        arabic = "أربعة نطاقات تخطيط · من دون نص حر",
    ),
    FIELD_INVESTMENT(
        english = "Initial UAE investment",
        simplifiedChinese = "UAE 初始投资",
        arabic = "الاستثمار الأولي في الإمارات",
    ),
    FIELD_INVESTMENT_HINT(
        english = "How much capital is available for the initial UAE launch?",
        simplifiedChinese = "UAE 初次启动可用资金是多少？",
        arabic = "ما حجم رأس المال المتاح للإطلاق الأولي في الإمارات؟",
    ),
    FIELD_INVESTMENT_PLACEHOLDER(
        english = "Select a budget band…",
        simplifiedChinese = "选择预算区间…",
        arabic = "اختر نطاق الميزانية…",
    ),
    BAND_UNDER_250K(
        english = "Under AED 250,000",
        simplifiedChinese = "低于 AED 250,000",
        arabic = "أقل من 250,000 درهم إماراتي",
    ),
    BAND_250K_499K(
        english = "AED 250,000–499,999",
        simplifiedChinese = "AED 250,000–499,999",
        arabic = "250,000–499,999 درهماً إماراتياً",
    ),
    BAND_500K_999K(
        english = "AED 500,000–999,999",
        simplifiedChinese = "AED 500,000–999,999",
        arabic = "500,000–999,999 درهماً إماراتياً",
    ),
    BAND_1M_2_49M(
        english = "AED 1–2.49 million",
        simplifiedChinese = "AED 100万–249万",
        arabic = "1–2.49 مليون درهم إماراتي",
    ),
    BAND_2_5M_4_99M(
        english = "AED 2.5–4.99 million",
        simplifiedChinese = "AED 250万–499万",
        arabic = "2.5–4.99 مليون درهم إماراتي",
    ),
    BAND_5M_PLUS(
        english = "AED 5 million+",
        simplifiedChinese = "AED 500万以上",
        arabic = "5 ملايين درهم إماراتي فأكثر",
    ),
    BAND_NOT_SURE(
        english = "Not sure yet",
        simplifiedChinese = "尚不确定",
        arabic = "لست متأكداً بعد",
    ),
    FIELD_REVENUE(
        english = "12-month UAE revenue",
        simplifiedChinese = "UAE 市场 12 个月营收",
        arabic = "إيرادات 12 شهراً في الإمارات",
    ),
    FIELD_REVENUE_HINT(
        english = "What revenue range is the first-year plan targeting?",
        simplifiedChinese = "首年计划的目标营收区间是多少？",
        arabic = "ما نطاق الإيرادات المستهدف في خطة العام الأول؟",
    ),
    FIELD_REVENUE_PLACEHOLDER(
        english = "Select a revenue band…",
        simplifiedChinese = "选择营收区间…",
        arabic = "اختر نطاق الإيرادات…",
    ),
    REVENUE_PRE_REVENUE(
        english = "Pre-revenue",
        simplifiedChinese = "尚未产生营收",
        arabic = "قبل تحقيق الإيرادات",
    ),
    FIELD_TEAM(
        english = "Local team size",
        simplifiedChinese = "本地团队规模",
        arabic = "حجم الفريق المحلي",
    ),
    FIELD_TEAM_HINT(
        english = "How many UAE-based team members are planned?",
        simplifiedChinese = "计划配备多少名驻 UAE 团队成员？",
        arabic = "كم عدد أعضاء الفريق المخطط لوجودهم في الإمارات؟",
    ),
    FIELD_TEAM_PLACEHOLDER(
        english = "Select a team band…",
        simplifiedChinese = "选择团队规模区间…",
        arabic = "اختر نطاق حجم الفريق…",
    ),
    TEAM_ZERO(
        english = "0 — founders or remote team only",
        simplifiedChinese = "0 人 — 仅创始人或远程团队",
        arabic = "0 — المؤسسون أو فريق عن بُعد فقط",
    ),
    TEAM_ONE_THREE(
        english = "1–3 people",
        simplifiedChinese = "1–3 人",
        arabic = "1–3 أشخاص",
    ),
    TEAM_FOUR_NINE(
        english = "4–9 people",
        simplifiedChinese = "4–9 人",
        arabic = "4–9 أشخاص",
    ),
    TEAM_TEN_TWENTY_FOUR(
        english = "10–24 people",
        simplifiedChinese = "10–24 人",
        arabic = "10–24 شخصاً",
    ),
    TEAM_TWENTY_FIVE_FORTY_NINE(
        english = "25–49 people",
        simplifiedChinese = "25–49 人",
        arabic = "25–49 شخصاً",
    ),
    TEAM_FIFTY_PLUS(
        english = "50+ people",
        simplifiedChinese = "50 人以上",
        arabic = "50 شخصاً فأكثر",
    ),
    FIELD_VISAS(
        english = "Expected visas",
        simplifiedChinese = "预计签证数量",
        arabic = "التأشيرات المتوقعة",
    ),
    FIELD_VISAS_HINT(
        english = "How many residence or employment visas will be required?",
        simplifiedChinese = "需要多少份居留或工作签证？",
        arabic = "كم عدد تأشيرات الإقامة أو العمل المطلوبة؟",
    ),
    FIELD_VISAS_PLACEHOLDER(
        english = "Select a visa band…",
        simplifiedChinese = "选择签证数量区间…",
        arabic = "اختر نطاق التأشيرات…",
    ),
    VISAS_ZERO(
        english = "0 visas",
        simplifiedChinese = "0 份签证",
        arabic = "0 تأشيرة",
    ),
    VISAS_ONE_THREE(
        english = "1–3 visas",
        simplifiedChinese = "1–3 份签证",
        arabic = "1–3 تأشيرات",
    ),
    VISAS_FOUR_NINE(
        english = "4–9 visas",
        simplifiedChinese = "4–9 份签证",
        arabic = "4–9 تأشيرات",
    ),
    VISAS_TEN_TWENTY_FOUR(
        english = "10–24 visas",
        simplifiedChinese = "10–24 份签证",
        arabic = "10–24 تأشيرة",
    ),
    VISAS_TWENTY_FIVE_PLUS(
        english = "25+ visas",
        simplifiedChinese = "25 份以上签证",
        arabic = "25 تأشيرة فأكثر",
    ),
    ANALYSE_CITIES(
        english = "Analyse Dubai and Abu Dhabi",
        simplifiedChinese = "分析 Dubai 与 Abu Dhabi",
        arabic = "حلّل دبي وأبوظبي",
    ),
    SCALE_PROGRESS_TEMPLATE(
        english = "{answered} of 4 answered",
        simplifiedChinese = "已回答 {answered}/4",
        arabic = "تمت الإجابة عن {answered} من 4",
    ),
    ANALYSIS_IN_PROGRESS(
        english = "Assessment in progress",
        simplifiedChinese = "正在评估",
        arabic = "التقييم جارٍ",
    ),
    ANALYSIS_TITLE_BUILDING(
        english = "Building your Dubai vs Abu Dhabi comparison.",
        simplifiedChinese = "正在生成 Dubai 与 Abu Dhabi 的对比。",
        arabic = "نبني مقارنتك بين دبي وأبوظبي.",
    ),
    ANALYSIS_LEAD_BUILDING(
        english = "We’re scoring five decision groups using your project profile and curated UAE benchmark data.",
        simplifiedChinese = "我们正根据您的项目概况和精选 UAE 基准数据，对五个决策组进行评分。",
        arabic = "نقيّم خمس مجموعات قرار باستخدام ملف مشروعك وبيانات مرجعية مختارة للإمارات.",
    ),
    ANALYSIS_SCENARIO_A11Y(
        english = "Scenario being analysed",
        simplifiedChinese = "正在分析的情景",
        arabic = "السيناريو قيد التحليل",
    ),
    ANALYSIS_PROGRESS_A11Y(
        english = "Decision groups scored",
        simplifiedChinese = "已评分的决策组",
        arabic = "مجموعات القرار التي تم تقييمها",
    ),
    ANALYSIS_PROGRESS_ZERO(
        english = "0 of 5 groups scored",
        simplifiedChinese = "已评分 0/5 个决策组",
        arabic = "تم تقييم 0 من 5 مجموعات",
    ),
    ANALYSIS_HIGHER_STRONGER(
        english = "Higher = stronger fit",
        simplifiedChinese = "分数越高 = 契合度越强",
        arabic = "الأعلى = ملاءمة أقوى",
    ),
    ANALYSIS_CITY_STATUS_A11Y(
        english = "City analysis status",
        simplifiedChinese = "城市分析状态",
        arabic = "حالة تحليل المدينتين",
    ),
    ANALYSIS_WAITING_ELIGIBILITY(
        english = "Waiting for eligibility check",
        simplifiedChinese = "等待准入资格检查",
        arabic = "بانتظار فحص الأهلية",
    ),
    ANALYSIS_WAITING_GATE(
        english = "Waiting for eligibility gate",
        simplifiedChinese = "等待准入资格门槛检查",
        arabic = "بانتظار بوابة الأهلية",
    ),
    ANALYSIS_SKIP(
        english = "Skip animation",
        simplifiedChinese = "跳过动画",
        arabic = "تخطَّ الحركة",
    ),
    ANALYSIS_VIEW_RESULTS(
        english = "View Dubai vs Abu Dhabi",
        simplifiedChinese = "查看 Dubai 与 Abu Dhabi 的对比",
        arabic = "اعرض دبي مقابل أبوظبي",
    ),
    ANALYSIS_NOTE(
        english = "Eligibility is a gate, not a score. Group averages exclude N/A values; Market Signals and operating margin remain separate outputs.",
        simplifiedChinese = "准入资格是一项门槛，而非评分。决策组平均分不计 N/A 值；市场信号和营业利润率仍作为单独结果呈现。",
        arabic = "الأهلية بوابة وليست درجة. تستبعد متوسطات المجموعات القيم غير المنطبقة؛ وتبقى مؤشرات السوق وهامش التشغيل مخرجين منفصلين.",
    ),
    ANALYSIS_DECISION_GROUP(
        english = "Decision group",
        simplifiedChinese = "决策组",
        arabic = "مجموعة القرار",
    ),
    ANALYSIS_GROUP_AVERAGE(
        english = "Group avg.",
        simplifiedChinese = "组平均分",
        arabic = "متوسط المجموعة",
    ),
    ANALYSIS_EVALUATING(
        english = "Evaluating both cities against the same inputs…",
        simplifiedChinese = "正在使用相同输入评估两个城市…",
        arabic = "جارٍ تقييم المدينتين وفق المدخلات نفسها…",
    ),
    ANALYSIS_INPUTS_QUEUED(
        english = "Inputs queued for evaluation.",
        simplifiedChinese = "输入已排队等待评估。",
        arabic = "المدخلات في قائمة انتظار التقييم.",
    ),
    ANALYSIS_READY_LABEL(
        english = "Assessment ready",
        simplifiedChinese = "评估已就绪",
        arabic = "التقييم جاهز",
    ),
    ANALYSIS_TITLE_READY(
        english = "Your city comparison is ready.",
        simplifiedChinese = "您的城市对比已就绪。",
        arabic = "مقارنة المدينتين جاهزة.",
    ),
    ANALYSIS_LEAD_READY(
        english = "Open any group to inspect its scoring inputs, or continue to the full assessment.",
        simplifiedChinese = "打开任一决策组查看其评分输入，或继续查看完整评估。",
        arabic = "افتح أي مجموعة لمراجعة مدخلات تقييمها، أو تابع إلى التقييم الكامل.",
    ),
    ANALYSIS_CHECKING(
        english = "Checking…",
        simplifiedChinese = "检查中…",
        arabic = "جارٍ التحقق…",
    ),
    ANALYSIS_PENDING(
        english = "Pending",
        simplifiedChinese = "待处理",
        arabic = "معلّق",
    ),
    ANALYSIS_SCORED(
        english = "Scored",
        simplifiedChinese = "已评分",
        arabic = "تم التقييم",
    ),
    ANALYSIS_SCORING(
        english = "Scoring",
        simplifiedChinese = "评分中",
        arabic = "جارٍ التقييم",
    ),
    ANALYSIS_QUEUED(
        english = "Queued",
        simplifiedChinese = "排队中",
        arabic = "في قائمة الانتظار",
    ),
    ANALYSIS_ELIGIBLE_CONDITIONAL(
        english = "Conditionally eligible · approvals identified",
        simplifiedChinese = "有条件符合准入资格 · 已识别所需审批",
        arabic = "مؤهل بشروط · تم تحديد الموافقات",
    ),
    ANALYSIS_CHECKING_GATE(
        english = "Checking eligibility gate",
        simplifiedChinese = "正在检查准入资格门槛",
        arabic = "جارٍ التحقق من بوابة الأهلية",
    ),
    ANALYSIS_ELIGIBLE_APPROVALS(
        english = "Eligible · approvals required",
        simplifiedChinese = "符合准入资格 · 需要审批",
        arabic = "مؤهل · الموافقات مطلوبة",
    ),
    GROUP_REGULATORY_TITLE(
        english = "Regulatory & Setup Fit",
        simplifiedChinese = "监管与设立契合度",
        arabic = "ملاءمة المتطلبات التنظيمية والتأسيس",
    ),
    GROUP_REGULATORY_SUBTITLE(
        english = "Lawful access, licensing and operating permissions",
        simplifiedChinese = "合法准入、许可与运营权限",
        arabic = "الدخول القانوني والتراخيص وتصاريح التشغيل",
    ),
    GROUP_REGULATORY_INSIGHT(
        english = "The route is viable in both cities; additional approvals create the most friction.",
        simplifiedChinese = "该路径在两个城市均可行；额外审批是最大的阻力来源。",
        arabic = "المسار قابل للتنفيذ في المدينتين؛ وتشكّل الموافقات الإضافية أكبر موضع للتعقيد.",
    ),
    GROUP_COST_TITLE(
        english = "Cost Attractiveness",
        simplifiedChinese = "成本吸引力",
        arabic = "جاذبية التكلفة",
    ),
    GROUP_COST_SUBTITLE(
        english = "Setup and recurring costs relative to the scenario",
        simplifiedChinese = "相对于该情景的设立与经常性成本",
        arabic = "تكاليف التأسيس والتكاليف المتكررة نسبةً إلى السيناريو",
    ),
    GROUP_COST_INSIGHT(
        english = "Largest difference: premises and utilities favour Abu Dhabi in this scenario.",
        simplifiedChinese = "最大差异：在此情景下，经营场所和公用事业成本更有利于 Abu Dhabi。",
        arabic = "أكبر فارق: المقر والمرافق يرجّحان أبوظبي في هذا السيناريو.",
    ),
    GROUP_OPPORTUNITY_TITLE(
        english = "Market Opportunity",
        simplifiedChinese = "市场机会",
        arabic = "فرصة السوق",
    ),
    GROUP_OPPORTUNITY_SUBTITLE(
        english = "Demand, customer access and competitive headroom",
        simplifiedChinese = "需求、客户触达与竞争空间",
        arabic = "الطلب والوصول إلى العملاء والمساحة التنافسية المتاحة",
    ),
    GROUP_OPPORTUNITY_INSIGHT(
        english = "Largest difference: addressable demand and customer access favour Dubai.",
        simplifiedChinese = "最大差异：可触达需求与客户触达更有利于 Dubai。",
        arabic = "أكبر فارق: الطلب الممكن الوصول إليه والوصول إلى العملاء يرجّحان دبي.",
    ),
    GROUP_OPERATIONS_TITLE(
        english = "Operating Environment",
        simplifiedChinese = "运营环境",
        arabic = "بيئة التشغيل",
    ),
    GROUP_OPERATIONS_SUBTITLE(
        english = "Infrastructure, logistics and facility suitability",
        simplifiedChinese = "基础设施、物流与设施适用性",
        arabic = "البنية التحتية والخدمات اللوجستية وملاءمة المنشآت",
    ),
    GROUP_OPERATIONS_INSIGHT(
        english = "Dubai leads on regional connectivity; Abu Dhabi leads on facility suitability.",
        simplifiedChinese = "Dubai 在区域互联互通方面领先；Abu Dhabi 在设施适用性方面领先。",
        arabic = "تتقدم دبي في الترابط الإقليمي؛ وتتقدم أبوظبي في ملاءمة المنشآت.",
    ),
    GROUP_TALENT_TITLE(
        english = "Talent & Ecosystem",
        simplifiedChinese = "人才与生态系统",
        arabic = "المواهب والمنظومة",
    ),
    GROUP_TALENT_SUBTITLE(
        english = "People, partners and specialist support capacity",
        simplifiedChinese = "人才、合作伙伴与专业支持能力",
        arabic = "الأفراد والشركاء وقدرة الدعم المتخصص",
    ),
    GROUP_TALENT_INSIGHT(
        english = "Dubai’s deeper commercial-services network offsets higher employment cost.",
        simplifiedChinese = "Dubai 更成熟的商业服务网络抵消了较高的雇佣成本。",
        arabic = "تعوّض شبكة الخدمات التجارية الأعمق في دبي ارتفاع تكلفة التوظيف.",
    ),
    CONFIDENCE_HIGH(
        english = "High",
        simplifiedChinese = "高",
        arabic = "عالية",
    ),
    CONFIDENCE_MEDIUM(
        english = "Medium",
        simplifiedChinese = "中",
        arabic = "متوسطة",
    ),
    METRIC_ACTIVITY_ELIGIBILITY(
        english = "Activity eligibility",
        simplifiedChinese = "经营活动准入资格",
        arabic = "أهلية النشاط",
    ),
    METRIC_HARD_GATE(
        english = "Hard gate",
        simplifiedChinese = "硬性门槛",
        arabic = "شرط حاسم",
    ),
    METRIC_PASS(
        english = "Pass",
        simplifiedChinese = "通过",
        arabic = "مستوفى",
    ),
    METRIC_LICENSING_PATH(
        english = "Licensing path",
        simplifiedChinese = "许可路径",
        arabic = "مسار الترخيص",
    ),
    METRIC_OFFICIAL_RULE(
        english = "Official rule",
        simplifiedChinese = "官方规定",
        arabic = "قاعدة رسمية",
    ),
    METRIC_LEGAL_FORM(
        english = "Legal form & ownership",
        simplifiedChinese = "法律形式与所有权",
        arabic = "الشكل القانوني والملكية",
    ),
    METRIC_BUSINESS_LOCATION(
        english = "Business location & premises",
        simplifiedChinese = "经营地点与场所",
        arabic = "موقع النشاط والمقر",
    ),
    METRIC_ADDITIONAL_APPROVALS(
        english = "Additional authority approvals",
        simplifiedChinese = "其他主管部门审批",
        arabic = "موافقات الجهات الإضافية",
    ),
    METRIC_PRODUCT_OBLIGATIONS(
        english = "Product / service obligations",
        simplifiedChinese = "产品 / 服务义务",
        arabic = "التزامات المنتج / الخدمة",
    ),
    METRIC_TAX_CUSTOMS(
        english = "Tax & customs registration",
        simplifiedChinese = "税务与海关登记",
        arabic = "التسجيل الضريبي والجمركي",
    ),
    METRIC_FEDERAL_RULE(
        english = "Federal rule",
        simplifiedChinese = "联邦规定",
        arabic = "قاعدة اتحادية",
    ),
    METRIC_EMPLOYMENT_VISAS(
        english = "Employment & visa compliance",
        simplifiedChinese = "雇佣与签证合规",
        arabic = "الامتثال للعمل والتأشيرات",
    ),
    METRIC_LICENCE_SETUP_COST(
        english = "Licence & setup cost",
        simplifiedChinese = "许可证与设立成本",
        arabic = "تكلفة الترخيص والتأسيس",
    ),
    METRIC_OFFICIAL_FEE(
        english = "Official fee",
        simplifiedChinese = "官方费用",
        arabic = "رسم رسمي",
    ),
    METRIC_WORKSPACE_FACILITY(
        english = "Workspace or facility",
        simplifiedChinese = "办公空间或设施",
        arabic = "مساحة عمل أو منشأة",
    ),
    METRIC_BENCHMARK(
        english = "Benchmark",
        simplifiedChinese = "基准数据",
        arabic = "مرجع قياسي",
    ),
    METRIC_PAYROLL_BENEFITS(
        english = "Payroll & benefits",
        simplifiedChinese = "薪资与福利",
        arabic = "الرواتب والمزايا",
    ),
    METRIC_VISAS_INSURANCE(
        english = "Visas & insurance",
        simplifiedChinese = "签证与保险",
        arabic = "التأشيرات والتأمين",
    ),
    METRIC_UTILITIES_CONNECTIVITY(
        english = "Utilities & connectivity",
        simplifiedChinese = "公用事业与连接服务",
        arabic = "المرافق والاتصال",
    ),
    METRIC_DELIVERY_FULFILMENT(
        english = "Delivery & fulfilment",
        simplifiedChinese = "配送与履约",
        arabic = "التسليم وتنفيذ الطلبات",
    ),
    METRIC_RENEWAL_COMPLIANCE(
        english = "Renewal & compliance overhead",
        simplifiedChinese = "续期与合规管理成本",
        arabic = "أعباء التجديد والامتثال",
    ),
    METRIC_MODEL_INPUT(
        english = "Model input",
        simplifiedChinese = "模型输入",
        arabic = "مدخل للنموذج",
    ),
    METRIC_SECTOR_DEMAND(
        english = "Sector demand",
        simplifiedChinese = "行业需求",
        arabic = "طلب القطاع",
    ),
    METRIC_MARKET_DATA(
        english = "Market data",
        simplifiedChinese = "市场数据",
        arabic = "بيانات السوق",
    ),
    METRIC_GROWTH_MOMENTUM(
        english = "Growth momentum",
        simplifiedChinese = "增长势头",
        arabic = "زخم النمو",
    ),
    METRIC_WILLINGNESS_TO_PAY(
        english = "Willingness to pay",
        simplifiedChinese = "支付意愿",
        arabic = "الاستعداد للدفع",
    ),
    METRIC_CUSTOMER_ACCESS(
        english = "Customer access",
        simplifiedChinese = "客户触达",
        arabic = "الوصول إلى العملاء",
    ),
    METRIC_CHANNEL_FIT(
        english = "Channel fit",
        simplifiedChinese = "渠道契合度",
        arabic = "ملاءمة القنوات",
    ),
    METRIC_COMPETITIVE_HEADROOM(
        english = "Competitive headroom",
        simplifiedChinese = "竞争空间",
        arabic = "المساحة التنافسية المتاحة",
    ),
    METRIC_REGIONAL_CONNECTIVITY(
        english = "Regional connectivity",
        simplifiedChinese = "区域互联互通",
        arabic = "الترابط الإقليمي",
    ),
    METRIC_LOGISTICS_NETWORK(
        english = "Logistics network",
        simplifiedChinese = "物流网络",
        arabic = "الشبكة اللوجستية",
    ),
    METRIC_INFRASTRUCTURE_RELIABILITY(
        english = "Infrastructure reliability",
        simplifiedChinese = "基础设施可靠性",
        arabic = "موثوقية البنية التحتية",
    ),
    METRIC_FACILITY_SUITABILITY(
        english = "Facility suitability",
        simplifiedChinese = "设施适用性",
        arabic = "ملاءمة المنشآت",
    ),
    METRIC_DIGITAL_PAYMENTS(
        english = "Digital & payment infrastructure",
        simplifiedChinese = "数字与支付基础设施",
        arabic = "البنية التحتية الرقمية والمدفوعات",
    ),
    METRIC_TALENT_AVAILABILITY(
        english = "Relevant talent availability",
        simplifiedChinese = "相关人才供给",
        arabic = "توافر المواهب ذات الصلة",
    ),
    METRIC_LABOUR_DATA(
        english = "Labour data",
        simplifiedChinese = "劳动力数据",
        arabic = "بيانات سوق العمل",
    ),
    METRIC_SECTOR_EXPERTISE(
        english = "Sector-specific expertise",
        simplifiedChinese = "行业专业能力",
        arabic = "خبرة متخصصة في القطاع",
    ),
    METRIC_PARTNER_ECOSYSTEM(
        english = "Partner ecosystem",
        simplifiedChinese = "合作伙伴生态系统",
        arabic = "منظومة الشركاء",
    ),
    METRIC_RELOCATION_RETENTION(
        english = "Relocation & retention fit",
        simplifiedChinese = "搬迁与人才留任契合度",
        arabic = "ملاءمة الانتقال والاحتفاظ بالمواهب",
    ),
    METRIC_PROFESSIONAL_SERVICES(
        english = "Professional services depth",
        simplifiedChinese = "专业服务深度",
        arabic = "عمق الخدمات المهنية",
    ),
    METRIC_SUBMETRIC(
        english = "Submetric",
        simplifiedChinese = "子指标",
        arabic = "مؤشر فرعي",
    ),
    ANALYSIS_PROGRESS_TEMPLATE(
        english = "{scored} of {total} groups scored",
        simplifiedChinese = "已完成 {scored}/{total} 个决策组的评分",
        arabic = "تم تقييم {scored} من {total} مجموعات",
    ),
    ANALYSIS_GROUP_WEIGHT_TEMPLATE(
        english = "Group average · Overall weight {weight}%",
        simplifiedChinese = "组平均分 · 总权重 {weight}%",
        arabic = "متوسط المجموعة · الوزن الإجمالي {weight}%",
    ),
    ANALYSIS_INPUTS_CONFIDENCE_TEMPLATE(
        english = "{count} inputs · Confidence: {confidence}",
        simplifiedChinese = "{count} 项输入 · 置信度：{confidence}",
        arabic = "{count} مدخلات · مستوى الثقة: {confidence}",
    ),
    RESULTS_TITLE(
        english = "Dubai and Abu Dhabi, side by side.",
        simplifiedChinese = "并排比较 Dubai 与 Abu Dhabi。",
        arabic = "دبي وأبوظبي، جنباً إلى جنب.",
    ),
    RESULTS_LEAD(
        english = "Compare the modelled city-fit score with the softer signals that can shape how each market feels in practice.",
        simplifiedChinese = "将城市契合度模型评分与更具感知性的信号进行比较，了解各市场在实际运营中的体验。",
        arabic = "قارن درجة ملاءمة المدينة الناتجة عن النموذج بالمؤشرات النوعية التي قد تشكّل الانطباع العملي عن كل سوق.",
    ),
    RESULTS_ASSUMPTIONS_NOTE(
        english = "Curated demo assumptions · not a forecast or professional advice.",
        simplifiedChinese = "基于精选演示假设 · 不构成预测或专业建议。",
        arabic = "افتراضات توضيحية مختارة · ليست توقعاً أو مشورة مهنية.",
    ),
    RESULTS_SELECTED_SCENARIO_A11Y(
        english = "Selected scenario",
        simplifiedChinese = "所选情景",
        arabic = "السيناريو المحدد",
    ),
    RESULTS_HARD_TITLE(
        english = "Weighted market-entry score",
        simplifiedChinese = "加权市场进入评分",
        arabic = "الدرجة المرجّحة لدخول السوق",
    ),
    RESULTS_HARD_BODY(
        english = "A weighted average of the five decision groups for the selected entry route. Higher means a stronger modelled fit.",
        simplifiedChinese = "针对所选进入路径，对五个决策组进行加权平均。分数越高，表示模型契合度越强。",
        arabic = "متوسط مرجّح لمجموعات القرار الخمس لمسار الدخول المحدد. كلما ارتفعت الدرجة كانت الملاءمة المقدّرة أقوى.",
    ),
    RESULTS_HARD_SCALE(
        english = "Hard scale · out of 100",
        simplifiedChinese = "硬指标 · 满分 100",
        arabic = "مقياس كمي · من 100",
    ),
    RESULTS_OUT_OF_100(
        english = "/ 100",
        simplifiedChinese = "/ 100",
        arabic = "/ 100",
    ),
    RESULTS_DUBAI_HARD_BODY(
        english = "Demand, connectivity and ecosystem depth lift the overall fit.",
        simplifiedChinese = "需求、互联互通和生态系统深度提升了整体契合度。",
        arabic = "يرفع الطلب والترابط وعمق المنظومة مستوى الملاءمة الإجمالي.",
    ),
    RESULTS_ABU_HARD_BODY(
        english = "Cost attractiveness and facility fit keep the city highly competitive.",
        simplifiedChinese = "成本吸引力与设施契合度使该城市保持较强竞争力。",
        arabic = "تحافظ جاذبية التكلفة وملاءمة المرافق على قدرة المدينة التنافسية العالية.",
    ),
    RESULTS_CALCULATING_GAP(
        english = "Calculating the gap…",
        simplifiedChinese = "正在计算差距…",
        arabic = "جارٍ حساب الفارق…",
    ),
    RESULTS_SIGNALS_EXCLUDED(
        english = "Market Signals are deliberately excluded from this weighted score.",
        simplifiedChinese = "该加权评分有意不计入市场信号。",
        arabic = "استُبعدت مؤشرات السوق عمداً من هذه الدرجة المرجّحة.",
    ),
    RESULTS_BREAKDOWN_A11Y(
        english = "Weighted score breakdown",
        simplifiedChinese = "加权评分明细",
        arabic = "تفصيل الدرجة المرجّحة",
    ),
    RESULTS_DECISION_GROUP_WEIGHT(
        english = "Decision group and weight",
        simplifiedChinese = "决策组及权重",
        arabic = "مجموعة القرار ووزنها",
    ),
    RESULTS_SOFT_TITLE(
        english = "Market sentiment summary",
        simplifiedChinese = "市场情绪摘要",
        arabic = "ملخص توجهات السوق",
    ),
    RESULTS_SOFT_BODY(
        english = "General city-level context from directional customer, operator, talent and partner signals—not verified facts or forecasts.",
        simplifiedChinese = "基于客户、运营方、人才和合作伙伴方向性信号的城市层面概况；并非经核实的事实或预测。",
        arabic = "سياق عام على مستوى المدينة مستمد من مؤشرات اتجاهية للعملاء والمشغّلين والمواهب والشركاء، وليس حقائق أو توقعات مؤكدة.",
    ),
    RESULTS_SOFT_SCALE(
        english = "Soft scale · context only",
        simplifiedChinese = "软指标 · 仅供背景参考",
        arabic = "مقياس نوعي · للسياق فقط",
    ),
    RESULTS_DUBAI_SENTIMENT_TAGLINE(
        english = "Energetic and opportunity-led",
        simplifiedChinese = "活跃且由机遇驱动",
        arabic = "حيوي ومدفوع بالفرص",
    ),
    RESULTS_STRONGLY_POSITIVE(
        english = "Strongly positive",
        simplifiedChinese = "非常积极",
        arabic = "إيجابي جداً",
    ),
    RESULTS_DUBAI_SENTIMENT_A11Y(
        english = "Dubai market sentiment: strongly positive",
        simplifiedChinese = "Dubai 市场情绪：非常积极",
        arabic = "توجهات سوق دبي: إيجابية جداً",
    ),
    RESULTS_CAUTIOUS(
        english = "Cautious",
        simplifiedChinese = "谨慎",
        arabic = "حذر",
    ),
    RESULTS_MIXED(
        english = "Mixed",
        simplifiedChinese = "喜忧参半",
        arabic = "مختلط",
    ),
    RESULTS_POSITIVE(
        english = "Positive",
        simplifiedChinese = "积极",
        arabic = "إيجابي",
    ),
    RESULTS_DUBAI_SENTIMENT_SUMMARY(
        english = "Directional signals suggest Dubai may feel visible, fast-moving and opportunity-rich.",
        simplifiedChinese = "方向性信号表明，Dubai 的市场曝光度可能更高、节奏更快且机会丰富。",
        arabic = "تشير المؤشرات الاتجاهية إلى أن دبي قد تبدو بارزة وسريعة الحركة وغنية بالفرص.",
    ),
    RESULTS_CUSTOMER_SENTIMENT(
        english = "Customer sentiment",
        simplifiedChinese = "客户情绪",
        arabic = "توجهات العملاء",
    ),
    RESULTS_DUBAI_CUSTOMER_SENTIMENT(
        english = "More upbeat; buyers may be easier to reach, with high expectations for speed and differentiation.",
        simplifiedChinese = "更为乐观；买方可能更容易触达，同时对速度和差异化有较高期望。",
        arabic = "أكثر تفاؤلاً؛ قد يكون الوصول إلى المشترين أسهل، مع توقعات مرتفعة للسرعة والتميّز.",
    ),
    RESULTS_TALENT_PARTNER_SENTIMENT(
        english = "Talent and partner sentiment",
        simplifiedChinese = "人才与合作伙伴情绪",
        arabic = "توجهات المواهب والشركاء",
    ),
    RESULTS_DUBAI_TALENT_SENTIMENT(
        english = "More confident; specialist hiring and partner discovery may feel easier in a highly visible ecosystem.",
        simplifiedChinese = "信心更强；在高曝光度的生态系统中，专业人才招聘和合作伙伴发掘可能更容易。",
        arabic = "ثقة أكبر؛ قد يبدو توظيف المتخصصين والعثور على الشركاء أسهل في منظومة عالية الحضور.",
    ),
    RESULTS_COMPETITIVE_INTENSITY(
        english = "Competitive intensity",
        simplifiedChinese = "竞争强度",
        arabic = "شدة المنافسة",
    ),
    RESULTS_DUBAI_COMPETITIVE_INTENSITY(
        english = "High pressure; stronger demand is perceived alongside a crowded, fast-moving market.",
        simplifiedChinese = "压力较高；更强的需求与拥挤、快速变化的市场并存。",
        arabic = "ضغط مرتفع؛ يُنظر إلى الطلب على أنه أقوى بالتوازي مع سوق مزدحمة وسريعة الحركة.",
    ),
    RESULTS_ABU_SENTIMENT_TAGLINE(
        english = "Confident and stability-led",
        simplifiedChinese = "稳健且由稳定性驱动",
        arabic = "واثق ومدفوع بالاستقرار",
    ),
    RESULTS_ABU_SENTIMENT_A11Y(
        english = "Abu Dhabi market sentiment: positive",
        simplifiedChinese = "Abu Dhabi 市场情绪：积极",
        arabic = "توجهات سوق أبوظبي: إيجابية",
    ),
    RESULTS_ABU_SENTIMENT_SUMMARY(
        english = "Directional signals suggest Abu Dhabi may feel measured, relationship-led and supportive of deliberate scale.",
        simplifiedChinese = "方向性信号表明，Abu Dhabi 的市场节奏可能更为审慎，以关系为导向，并支持稳步扩张。",
        arabic = "تشير المؤشرات الاتجاهية إلى أن أبوظبي قد تبدو متزنة وقائمة على العلاقات وداعمة للتوسع المدروس.",
    ),
    RESULTS_ABU_CUSTOMER_SENTIMENT(
        english = "Positive but deliberate; trust, references and relationship-building may matter more before purchase.",
        simplifiedChinese = "积极但审慎；在购买前，信任、参考案例和关系建立可能更为重要。",
        arabic = "إيجابي لكن متأنٍ؛ قد تكون الثقة والمراجع وبناء العلاقات أكثر أهمية قبل الشراء.",
    ),
    RESULTS_ABU_TALENT_SENTIMENT(
        english = "Constructive; networks may feel smaller and more relationship-led, with local credibility carrying more weight.",
        simplifiedChinese = "整体具有建设性；关系网络可能更小且更依赖人际关系，本地信誉的分量也更重。",
        arabic = "بنّاء؛ قد تبدو الشبكات أصغر وأكثر اعتماداً على العلاقات، مع وزن أكبر للمصداقية المحلية.",
    ),
    RESULTS_ABU_COMPETITIVE_INTENSITY(
        english = "Moderate pressure; the market may feel less saturated, although the addressable customer pool can feel narrower.",
        simplifiedChinese = "压力适中；市场饱和度可能较低，但可触达的客户群也可能更窄。",
        arabic = "ضغط متوسط؛ قد تبدو السوق أقل تشبعاً، مع أن قاعدة العملاء الممكن الوصول إليها قد تبدو أضيق.",
    ),
    RESULTS_MARKETS_FEEL_TITLE(
        english = "How the markets feel",
        simplifiedChinese = "市场感知如何",
        arabic = "الانطباع عن السوقين",
    ),
    RESULTS_MARKETS_FEEL_BODY(
        english = "Dubai feels faster, more visible and more competitive. Abu Dhabi feels more measured, relationship-led and cost-conscious.",
        simplifiedChinese = "Dubai 的节奏更快、曝光度更高、竞争也更激烈。Abu Dhabi 则更为审慎，以关系为导向且更注重成本。",
        arabic = "تبدو دبي أسرع وأكثر حضوراً ومنافسة. وتبدو أبوظبي أكثر اتزاناً واعتماداً على العلاقات ومراعاةً للتكلفة.",
    ),
    RESULTS_SOFT_NOTE(
        english = "The soft scale is contextual rather than predictive. Read it beside the weighted score; do not add it to the hard score.",
        simplifiedChinese = "软指标用于提供背景，而非预测。请结合加权评分阅读，不要将其计入硬指标评分。",
        arabic = "المقياس النوعي سياقي وليس تنبؤياً. اقرأه إلى جانب الدرجة المرجّحة، ولا تضفه إلى الدرجة الكمية.",
    ),
    RESULTS_VERDICT_CONTEXT(
        english = "Dubai’s soft signal is more energetic and demand-led; Abu Dhabi’s is steadier and stability-led. Use the hard score to compare operating fit and the soft scale to understand market mood.",
        simplifiedChinese = "Dubai 的软信号更活跃、由需求驱动；Abu Dhabi 的软信号更稳健、由稳定性驱动。请使用硬指标评分比较运营契合度，并通过软指标了解市场氛围。",
        arabic = "مؤشر دبي النوعي أكثر حيويةً واعتماداً على الطلب، بينما مؤشر أبوظبي أكثر ثباتاً واعتماداً على الاستقرار. استخدم الدرجة الكمية لمقارنة ملاءمة التشغيل، والمقياس النوعي لفهم مزاج السوق.",
    ),
    RESULTS_HARD_TIED(
        english = "The hard score is effectively tied.",
        simplifiedChinese = "硬指标评分基本持平。",
        arabic = "الدرجة الكمية متعادلة عملياً.",
    ),
    RESULTS_WEIGHT_TEMPLATE(
        english = "{weight}% weight",
        simplifiedChinese = "权重 {weight}%",
        arabic = "وزن {weight}%",
    ),
    RESULTS_NEAR_TIE_TEMPLATE(
        english = "Near tie · {gap} point gap",
        simplifiedChinese = "非常接近 · 相差 {gap} 分",
        arabic = "تقارب شديد · فارق {gap} نقطة",
    ),
    RESULTS_NARROW_LEAD_TEMPLATE(
        english = "Narrow lead · {leader} +{gap}",
        simplifiedChinese = "小幅领先 · {leader} +{gap}",
        arabic = "تقدّم طفيف · {leader} +{gap}",
    ),
    RESULTS_LEADS_TEMPLATE(
        english = "{leader} leads by {gap} points",
        simplifiedChinese = "{leader} 领先 {gap} 分",
        arabic = "تتقدم {leader} بفارق {gap} نقطة",
    ),
    RESULTS_HARD_NARROW_TEMPLATE(
        english = "{leader} leads narrowly on the weighted hard scale.",
        simplifiedChinese = "{leader} 在加权硬指标上小幅领先。",
        arabic = "تتقدم {leader} بفارق طفيف على المقياس الكمي المرجّح.",
    ),
    RESULTS_HARD_LEADS_TEMPLATE(
        english = "{leader} leads on the weighted hard scale.",
        simplifiedChinese = "{leader} 在加权硬指标上领先。",
        arabic = "تتقدم {leader} على المقياس الكمي المرجّح.",
    );
}

/** Lightweight commonMain string catalog with English fallback semantics. */
object Strings {
    val supportedLanguages: List<AppLanguage> = AppLanguage.entries

    fun get(
        key: TextKey,
        language: AppLanguage = AppLanguage.English,
    ): String = when (language) {
        AppLanguage.English -> key.english
        AppLanguage.SimplifiedChinese -> key.simplifiedChinese.ifBlank { key.english }
        AppLanguage.Arabic -> key.arabic.ifBlank { key.english }
    }

    operator fun invoke(
        key: TextKey,
        language: AppLanguage = AppLanguage.English,
    ): String = get(key, language)

    /** Replaces named placeholders such as `{company}` without platform formatting APIs. */
    fun format(
        key: TextKey,
        language: AppLanguage = AppLanguage.English,
        replacements: Map<String, Any?>,
    ): String = replacements.entries.fold(get(key, language)) { text, (name, value) ->
        text.replace("{$name}", value?.toString().orEmpty())
    }

    fun format(
        key: TextKey,
        language: AppLanguage = AppLanguage.English,
        vararg replacements: Pair<String, Any?>,
    ): String = format(key, language, replacements.toMap())
}
