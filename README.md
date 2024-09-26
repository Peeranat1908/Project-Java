# CS211 Project ภาคต้น 2567

## ชื่อทีม monday-i-miss-you

### สมาชิกในทีม
|  รหัสนิสิต  |    ชื่อ-นามสกุล (ชื่อเล่น)     |  GitHub username  |
|:-----------:|:------------------------------:|:-----------------:|
|  6610450170 |  ณัฐธีร์ พรศตทวีวัฒน์ (ออกัส)  |    augusyouthh    |
| 6610450323  |      พีรณัฐ เผ่าผม (เฟรม)      |   Peeranat1908    |
| 6610451044  |     ภูดิส คู่ตระกูล (พีท)      |    PetePoodit     |
| 6610451036  |     ภวัต หวังศิริสุข (นิค)     |      PawatW       |

## คลิปความก้าวหน้าของระบบ
| ครั้งที่                      |       กำหนดส่ง        | Youtube Link |
|-------------------------------|:---------------------:|--|
| ความก้าวหน้าของระบบครั้งที่ 1 | 9 ส.ค. 2567 17:00 น.  | https://www.youtube.com/watch?v=27ETe45RVfM&feature=youtu.be |
| ความก้าวหน้าของระบบครั้งที่ 2 | 6 ก.ย. 2567 17:00 น.  | https://www.youtube.com/watch?v=DR3s_1SLV-c |
| ความก้าวหน้าของระบบครั้งที่ 3 | 27 ก.ย. 2567 17:00 น. | ____________ |
| โครงงานที่สมบูรณ์             | 18 ต.ค. 2567 17:00 น. | ____________ |

## สรุปสิ่งที่พัฒนาในแต่ละครั้ง
### ความก้าวหน้าของระบบครั้งที่ 1
1. ณัฐธีร์ พรศตทวีวัฒน์ (ออกัส)
   * สร้างหน้า register สำหรับและนิสิต
   * สร้างปุ่มเชื่อมโยง2ปุ่มได้แก่ ปุ่มRegister จะเชื่อมโยงไปหาหน้าถัดไปคือหน้าmainของนิสิต และปุ่มlogin ที่จะกลับเข้าไปหาหน้าloginในกรณีที่นิสิตเคยลงทะเบียนมาแล้ว
2. พีรณัฐ เผ่าผม (เฟรม)
   * ทำหน้า first page เพื่อที่จะเชื่อมไปยังหน้า login page เพื่อให้หน้า user interface มีความน่าใช้มากขึ้น
   * ออกแบบหน้า ui วางโครงสร้างของ application ของทุกๆหน้า เพื่อที่จะทำไปใช้ในการ develop ต่อไปในอนาคต
   * ทำหน้า my team เพื่อแสดงข้อมูลของทีม และ ทำปุ่มเชื่อมกลับไปที่หน้า student
3. ภูดิส คู่ตระกูล (พีท)
   * สร้างหน้า main ของนิสิตเบื้องต้นผ่าน scene builder
   * วางรูปเเบบการใช้งานใน figma ไว้แล้วว่าส่วนประกอบมีอะไรบ้าง เรื่องของปุ่มและการใช้งานปุ่มแต่ละอัน
   * design หน้า my team เเสดงข้อมูลของสมาชิกในกลุ่มเเต่คน
4. ภวัต หวังศิริสุข (นิค)
    * สร้างหน้าlogin.fxml ผ่าน scene builder
    * ทำปุ่มเชื่อมไปยัง registerในกรณีที่นิสิตยังไม่ได้ลงทะเบียน
    * ปุ่ม login เชื่อมไปยังหน้าstudent ในเบื้องต้น

### ความก้าวหน้าของระบบครั้งที่ 2
1. ณัฐธีร์ พรศตทวีวัฒน์ (ออกัส)
    * ทำระบบลงทะเบียน(ระบบregister)สำหรับนิสิตจนเกือบสมบูรณ์สามารถWriteข้อมูลลงไฟล์CSVได้
    *และได้สร้างหน้าMainAdminเป็นหน้าหลักของระบบฝ่ายผู้ดูแลระบบ ในอนาคตจะมีฟังก์ชั่นที่สามารถแก้ไขข้อมูลอาจารย์-เจ้าหน้าที่ได้ครับ
2. ภวัต หวังศิริสุข (นิค)
    * ทำระบบ login เช็คusername,password,บทบาทในไฟล์ได้
    * ทำระบบ reset password แก้ไข password ในไฟล์ได้
    * สร้างหน้า faculty-data-Admin-page
3. ภูดิส คู่ตระกูล (พีท)
    * เพิ่มเติมในส่วนระบบของนิสิต มีหน้าเพิ่มคำร้องทั่วไปเพิ่มขึ้น student-appeal เเละ normal-appeal
    * สร้าง Model ของคำร้องทั่วไปเเล้ว(NormalAppeal , NormalAppealList) เเละจะทำการ inherit คำร้องประเภทอื่นในอนาคต
    * ในส่วนของหน้า normal-appeal สามารถเขียนเเละกดส่งข้อมูลคำร้องได้เเล้วเเต่จะเพิ่มให้สามารถโชว์เเละติดตามคำร้องได้ในอนาคต
<<<<<<< HEAD
    * และได้สร้างหน้าMainAdminเป็นหน้าหลักของระบบฝ่ายผู้ดูแลระบบ ในอนาคตจะมีฟังก์ชั่นที่สามารถแก้ไขข้อมูลอาจารย์-เจ้าหน้าที่ได้ครับ
4. พีรณัฐ เผ่าผม (เฟรม)
    * เพิ่มหน้า advisor มี table view สำหรับแสดงรายชื่อนิสิต
    * เพิ่มหน้ารายการคำร้องสำหรับแสดงคำร้องล่าสุดที่นักเรียนส่งมา
=======
4. ณัฐ เผ่าผม (เฟรม)
      เพิ่มหน้า advisor มี table view สำหรับแสดงรายชื่อนิสิต
      เพิ่มหน้ารายการคำร้องสำหรับแสดงคำร้องล่าสุดที่นักเรียนส่งมา
>>>>>>> release/3.0.0

### ความก้าวหน้าของระบบครั้งที่ 3
1. ณัฐธีร์ พรศตทวีวัฒน์ (ออกัส)
    * ทำหน้าUserProfileและส่งข้อมูลUserข้ามหน้า
    * เริ่มทำหน้าดูนิสิตในภาค(งานของเจ้าหน้าที่ภาค)
2. ภวัต หวังศิริสุข (นิค)
   * ทำหน้าแสดงผู้ใช้บัญชีทั้งหมด สามารถค้นหาตามข้อมูลผู้ใช้ได้และสามารถกรองตามบทบาทของบัญชีผู้ใช้ได้ 
     สามารถกดเข้าไปเพื่อดูข้อมูลของผู้ใช้ได้และสามารถระงับหรือปลดระงับบัญชีผู็ใช้ได้
   * ทำหน้าการจัดการเจ้าหน้าที่และอาจารย์โดยสามารถค้นหาตามข้อมูลและสามารถกดเข้าไปดูข้อมูลของบัญชีนั้นได้
     ในหน้าดูข้อมูลของเจ้าหน้าที่และอาจารย์สามารถแก้ไขข้อมูลบัญชีได้
   * ทำระบบเพิ่มข้อมูลเจ้าหน้าที่และอาจารย์สามารถเลือกตามบทบาทที่จะเพิ่มได้
3. ภูดิส คู่ตระกูล (พีท)
     * ระบบคำร้องมีครบ 3 ประเภทเเล้วประกอบด้วย  คำร้องทั่วไป, ใบลาพักการศึกษา , ขอลงทะเบียนเรียน
     * สามารถส่งคำร้องได้หมดทุกอันเเละเเสดงคำร้องที่เคยส่งไปได้ โดยสามารถกดปุ่มเพื่อเลือกประเภทของคำร้องที่จะต้องการจะดูได้
       เเละยังสามารถ search หาคำร้องได้จากหัวเรื่องหรือรายละเอียด เเละเรียงลำดับคำร้องตามที่ส่งล่าสุดเเล้ว รวมทั้งสามารถกดดูรายละเอียดคำร้องทั้งหมด
       โดยการกดที่กล่องคำร้องเเต่ละอันที่เเสดงได้เลย
     * ตัวของคำร้องที่เเสดงในหน้าติดตามคำร้องจะขึ้นตามของนักเรียนที่ Login เข้ามาใช้งาน
4. พีรณัฐ เผ่าผม (เฟรม)
   * ทำหน้า admin ในส่วนของข้อมูลคณะและสาขา สามารถแสดงข้อมูลคณะทั้งหมดได้และสามารถแยกไปตามสาขาได้และสามารถที่จะเพิ่มข้อมูลหรือแก้ไขข้อมูลคณะกับสาขาได้
   * ทำหน้า advisor ในส่วนของรายชื่อนิสิตในอาจารย์ที่ปรึกษา สามารถแสดงชื่อนิสิตทั้งหมดและสามารถค้นหานิสิตได้ผ่าน ชื่อ และ รหัสนิสิต
   * ทำหน้า faculty staff สามารถแสดงรายการคำน้องของนิสิตและสามารถแสดงข้อมูลผู้อนุมัติคำร้อง โดยสามารถแก้ไขและเพิ่มข้อมูลของผู้อนุมัติได้

### โครงงานที่สมบูรณ์
1. คนที่หนึ่ง ซึ่งยังไม่ใช่ (วัน)
    * อธิบายสิ่งที่เป็นประโยชน์ต่อระบบ และทำให้ทีมภูมิใจ ไม่จำกัดจำนวนข้อย่อย
    * อธิบายสิ่งที่เป็นประโยชน์ต่อระบบ และทำให้ทีมภูมิใจ ไม่จำกัดจำนวนข้อย่อย
2. คนที่สอง แค่ลองรักกัน (ทรู)
    * อธิบายสิ่งที่เป็นประโยชน์ต่อระบบ และทำให้ทีมภูมิใจ ไม่จำกัดจำนวนข้อย่อย
    * อธิบายสิ่งที่เป็นประโยชน์ต่อระบบ และทำให้ทีมภูมิใจ ไม่จำกัดจำนวนข้อย่อย
3. คนที่สาม ต้องห้ามตัวเอง (ทรี)
    * อธิบายสิ่งที่เป็นประโยชน์ต่อระบบ และทำให้ทีมภูมิใจ ไม่จำกัดจำนวนข้อย่อย
    * อธิบายสิ่งที่เป็นประโยชน์ต่อระบบ และทำให้ทีมภูมิใจ ไม่จำกัดจำนวนข้อย่อย
4. คนที่สี่ คนนี้แค่พี่น้อง (โฟร์)
    * อธิบายสิ่งที่เป็นประโยชน์ต่อระบบ และทำให้ทีมภูมิใจ ไม่จำกัดจำนวนข้อย่อย
    * อธิบายสิ่งที่เป็นประโยชน์ต่อระบบ และทำให้ทีมภูมิใจ ไม่จำกัดจำนวนข้อย่อย

## วิธีการติดตั้งและรันโปรแกรม
อธิบายวิธีการติดตั้ง และวิธีการรันโปรแกรม รวมถึงที่อยู่ของไฟล์ pdf

## ตัวอย่างข้อมูลผู้ใช้ระบบ (username, password) 

## การวางโครงสร้างไฟล์ของโครงงาน
