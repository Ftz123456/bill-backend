package com.zsc.edu.bill.modules.system.controller;


/**
 * @author ftz
 * 票据Controller
 * 创建时间:11/1/2024 上午10:57
 * 描述: 针对表【ticket(票据表)】的数据库操作Controller
 */
@AllArgsConstructor
@RestController
@RequestMapping("api/rest/ticket")
public class TicketController {
    private final TicketService service;
    /**
     * 分页查询票据列表
     * @return 票据列表
     */
    @GetMapping("list")
    public PageDto<TicketVo> list(TicketQuery query){
      return service.tickePage(query);
  }
      /**
       * 创建票据
       * @return ture/false
       */
    @PostMapping("create")
    public Boolean create(Ticket ticket){
        return service.save(ticket);
    }
    /**
     * 更新票据
     * @return ture/false
     */
    @PostMapping("update")
    public Boolean update(Ticket ticket){
        return service.updateById(ticket);
    }
    /**
     * 删除票据
     * @return ture/false
     */
    @PostMapping("delete")
    public Boolean delete(Long id){
        return service.removeById(id);
    }
    /**
     * 批量删除票据
     * @return ture/false
     */
    @PostMapping("deleteBatch")
    public Boolean deleteBatch(List<Long> ids){
        return service.removeByIds(ids);
    }
    /**
     * 获取票据详情
     * @return 票据详情
     */
    @GetMapping("detail")
    public Ticket detail(Long id){
        return service.getById(id);
    }

}
