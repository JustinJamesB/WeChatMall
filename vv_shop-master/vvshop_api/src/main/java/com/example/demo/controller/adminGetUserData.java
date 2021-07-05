//package com.example.demo.controller;
//
//import com.example.demo.entity.PageBaseInfo;
//import com.github.pagehelper.PageHelper;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//public class adminGetUserData {
//
//    @PostMapping("/list")
//    @ResponseBody
//    public PageBaseInfo findStudentList(@RequestParam(required = false, defaultValue = "1") int page,
//                                        @RequestParam(required = false, defaultValue = "10") int rows){
//        try {
//            /*
//             * 分页设置
//             * page:当前页码  rows:显示条数
//             */
//            PageHelper.startPage(page, rows);
//            List<Student> list = studentService.list(student);
//            PageInfo<Student> pageInfo = new PageInfo<>(list);
//            // 总条数
//            int totalRow = (int) pageInfo.getTotal();
//            logger.info("StudentController invoke method findStudentList is SUCCESS!! result ==> {} " + JSON.toJSONString(list));
//            return new BaseResult<>(new PageBaseInfo(list,page,totalRow,rows));
//        } catch (Exception e) {
//            logger.error("StudentController invoke method findStudentList failed! cause : " + e);
//            return new BaseResult<>(ParamReturnEnum.ERROR_MSG_SYSTEM_ERROR.getCode(), ParamReturnEnum.ERROR_MSG_SYSTEM_ERROR.getMessage());
//        }
//    }
//}
